package com.tangula.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

class PermissionCheckResult(val allPass:Boolean,
                            val passedPermissions:List<String>,
                            val rejectPermission:List<String>
)

class PermissionUtils {

    companion object {

        private var grantCount = 0

        private fun nextGrantCount():Int{
            synchronized(PermissionUtils.Companion){
                return grantCount++
            }
        }

        private val WAITING_PERMISSION_REQUESTS = mutableMapOf<Int, (Array<out String>, IntArray)->Unit>()

        fun checkPermissions(permissions: List<String>):PermissionCheckResult {
            var allPass = true
            val passed = mutableListOf<String>()
            val rejected = mutableListOf<String>()

            for (it in permissions) {
                when (ActivityCompat.checkSelfPermission(ApplicationUtils.APP, it)) {
                    PackageManager.PERMISSION_GRANTED -> {
                        passed.add(it)
                    }
                    else -> {
                        rejected.add(it)
                        allPass = false
                    }
                }
            }
            return PermissionCheckResult(allPass, passed, rejected)
        }

        @SuppressLint("NewApi")
        fun requestPermissions(act: Activity, permissions: List<String>, callback:(Array<out String>, IntArray)->Unit){
            SdkVersionDecides.afterSdk23A6d0{
                val code = nextGrantCount()

                WAITING_PERMISSION_REQUESTS.put(code){ ps, res->
                    callback(ps, res)
                    WAITING_PERMISSION_REQUESTS.remove(code)
                }
                act.requestPermissions(permissions.toTypedArray(), code)
            }
        }

        fun onRequestPermisionsFinish(code:Int, permisssions:Array<out String>, res:IntArray){
            WAITING_PERMISSION_REQUESTS[code]?.also {
                it(permisssions, res)
            }
        }

        fun runHasAllPremissions(act:Activity, permissions: List<String>, task:()->Unit){
            checkPermissions(permissions).also {
                if(it.allPass){
                    task()
                }else{
                    requestPermissions(act,permissions){_,res->
                        var result = true
                        for(c in res){
                            result = result && c==PackageManager.PERMISSION_GRANTED
                            if(!result){
                                break
                            }
                        }
                        if(result){
                            task()
                        }
                    }
                }
            }
        }

        fun runHasAnyPremissions(act:Activity, permissions: List<String>, task:()->Unit){
            checkPermissions(permissions).also {
                if(it.allPass){
                    task()
                }else{
                    requestPermissions(act,permissions){_,res->
                        var result = false
                        for(c in res){
                            if(c==PackageManager.PERMISSION_GRANTED){
                                result = true
                                break
                            }
                        }
                        if(result){
                            task()
                        }
                    }
                }
            }
        }

        fun runHasAnyPremissionsNotWithRequestPermissions(permissions: List<String>, task:()->Unit){
            checkPermissions(permissions).also {
                if(it.allPass){
                    task()
                }
            }
        }

    }

}

class GpsPermissionsUtils{
    companion object {

        val GPS_PERMISSIONS = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        fun whenHasGpsPermissions(act:Activity, task:()->Unit){
            PermissionUtils.runHasAnyPremissions(act, GPS_PERMISSIONS, task)
        }

        fun whenHasGpsPermissionsNotWithRequestPermissions(task:()->Unit){
            PermissionUtils.runHasAnyPremissionsNotWithRequestPermissions(GPS_PERMISSIONS, task)
        }

    }
}

class CallTelPermissionUtils{
    companion object {
        fun whenHasPermissions(act:Activity, task:()->Unit){
            PermissionUtils.runHasAnyPremissions(act, listOf(Manifest.permission.CALL_PHONE), task)
        }
    }
}


class FilePermissionsUtils{
    companion object {
        fun whenHasReadAndWritePermissions(act:Activity, task:()->Unit){
            PermissionUtils.runHasAllPremissions(act, listOf(Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE ), task)
        }
    }
}

class NetwordPermissionsUtils{
    companion object {
        fun whenHasInternetPermissions(act:Activity, task:()->Unit){
            PermissionUtils.runHasAllPremissions(act, listOf(Manifest.permission.INTERNET ), task)
        }
    }
}

