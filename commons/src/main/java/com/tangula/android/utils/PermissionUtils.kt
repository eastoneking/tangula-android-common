package com.tangula.android.utils

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat

/**
 * 授权检查结果.
 */
class PermissionCheckResult(val allPass: Boolean,
                            val passedPermissions: List<String>,
                            val rejectPermission: List<String>
)

/**
 * 授权验证工具.
 */
@Suppress("UNUSED")
class PermissionUtils {

    companion object {

        /**
         * 本次运行中动态申请授权次数.
         */
        private var grantCount = 0

        /**
         * 获得下一次运行动态授权的Code.
         * @return 下次运行动态授权的Code.
         */
        private fun nextGrantCode(): Int {
            synchronized(PermissionUtils.Companion) {
                return ++grantCount
            }
        }

        private val WAITING_PERMISSION_REQUESTS = mutableMapOf<Int, (Array<out String>, IntArray) -> Unit>()

        /**
         * 检查列表中权限是否都被授予.
         * @param[permissions] 被检查的列表.
         * @return 检查结果.
         * 如果 [permissions]为空，返回结果中的allPass是true.
         */
        fun checkPermissions(permissions: List<String>): PermissionCheckResult {
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

        /**
         * 动态申请授权.
         *
         * 这个方法检查了SDK API是否大于等于23（Android 6.0），因为动态授权是Android6.0之后提供的.
         *
         * @param[act] 申请授权的Activity.
         * @param[permissions] 申请的授权项目.
         * @param[callback] 用户授权之后的回调函数.
         *
         * 这个参数是个方法，类型为：``(Array<out String>, IntArray)->Unit``，第一个参数是请求的授权
         * 列表，第二个参数是用户授权结果。开发人员应该在这个回调函数里对授权结果进行检查，当授权不
         * 能满足要求的时候，应该调用[rejectCallback].
         *
         * @param[rejectCallback] 用户拒绝授予相关条件时的处理函数.
         */
        @SuppressLint("NewApi")
        fun requestPermissions(act: Activity, permissions: List<String>, callback: (Array<out String>, IntArray) -> Unit, rejectCallback: (() -> Unit)?) {
            if (SdkVersionDecides.afterSdk23A6d0()) {
                val code = nextGrantCode()

                WAITING_PERMISSION_REQUESTS.put(code) { ps, res ->
                    callback(ps, res)
                    WAITING_PERMISSION_REQUESTS.remove(code)
                }

                act.requestPermissions(permissions.toTypedArray(), code)
            } else {
                rejectCallback?.also { it() }
            }
        }

        /**
         * 提供给Acvitiy在[Activity.onRequestPermissionsResult]方法中调用，处理用户授权结果的方法.
         *
         * 请直接在[Activity.onRequestPermissionsResult]方法中调用。
         *
         * @param[code] 请求调用时指定的Code.
         * @param[permisssions] 请求被授予权限的列表.
         * @param[res] 用户授权的结果.
         */
        fun onRequestPermisionsFinish(code: Int, permisssions: Array<out String>, res: IntArray) {
            WAITING_PERMISSION_REQUESTS[code]?.also {
                it(permisssions, res)
            }
        }

        /**
         * 当APP拥有所有[permissions]列表中的授权时运行[task]，否则运行[rejectCallback].
         *
         * 函数首先检查是否拥有所有授权，如果拥有全部授权，就执行[task]；否则，则请求授予缺失的授权，
         * 并且在用户授予全部确实的授权后，执行[task],如果用户仍然不授予全部权限，则执行
         * [rejectCallback].
         *
         * @param[act] 调用的Acvitity.
         * @param[permissions] 需要的授权列表.
         * @param[task] 拥有完整授权时要执行的任务.
         * @param[rejectCallback] 无法获得完整授权是执行的任务.
         *
         */
        fun whenHasAllPremissions(act: Activity, permissions: List<String>, task: () -> Unit, rejectCallback: (() -> Unit)?) {
            whenHasAllPremissionsNotWithRequestPermissions(permissions, task){
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, it.rejectPermission,
                        //授权之后的回调函数
                        { _, res ->
                            var result = true
                            for (c in res) {
                                result = result && c == PackageManager.PERMISSION_GRANTED
                                if (!result) {
                                    break
                                }
                            }
                            if (result) {
                                task()
                            } else {
                                rejectCallback?.apply { this() }
                            }
                        }, rejectCallback)
            }

        }

        /**
         * 当用户第一次授权所有[permissions]列表中的授权时运行[task]，否则运行[rejectCallback].
         *
         * 函数首先检查是否拥有所有授权，如果拥有全部授权，则什么都不执行；否则，则请求授予缺失的授权，
         * 并且在用户授予全部确实的授权后，执行[task],如果用户仍然不授予全部权限，则执行
         * [rejectCallback].
         *
         * 这是说系统在启动过程中，或者说本Activity出现之前，已经调用了某些涉及授权的代码，但是不能
         * 保证运行时是否已经启动，而这里的授权就是为了检查权限完整性，亡羊补牢.
         *
         * @param[act] 调用的Acvitity.
         * @param[permissions] 需要的授权列表.
         * @param[task] 拥有完整授权时要执行的任务.
         * @param[rejectCallback] 无法获得完整授权是执行的任务.
         *
         */
        fun whenFirstGrantPremissions(act: Activity, permissions: List<String>, task: () -> Unit, rejectCallback: (() -> Unit)?) {
            whenHasAllPremissionsNotWithRequestPermissions(permissions, {}){
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, it.rejectPermission,
                        //授权之后的回调函数
                        { _, res ->
                            var result = true
                            for (c in res) {
                                result = result && c == PackageManager.PERMISSION_GRANTED
                                if (!result) {
                                    break
                                }
                            }
                            if (result) {
                                task()
                            } else {
                                rejectCallback?.apply { this() }
                            }
                        }, rejectCallback)
            }

        }

        /**
         * 当APP拥有[permissions]列表中任意一项授权时运行[task]，否则运行[rejectCallback].
         *
         * 函数首先检查[permissions]列表中权限被授予的情况，如果被授予列表中不为空，不执行任何代码；
         * 否则，则请求授予[permissions]列表中所有权限，并且在用户授予其中任何一项时，执行[task],如
         * 果用户仍然不授予任何权限，则执行[rejectCallback].
         *
         * 这是说系统在启动过程中，或者说本Activity出现之前，已经调用了某些涉及授权的代码，但是不能
         * 保证运行时是否已经启动，而这里的授权就是为了检查权限完整性，亡羊补牢.
         *
         * @param[act] 调用的Acvitity.
         * @param[permissions] 需要检查的授权列表.
         * @param[task] 拥有任意一项授权时要执行的任务.
         * @param[rejectCallback] 无法获得任意一项授权是执行的任务.
         *
         */
        fun whenFirstGrantedAnyPremissions(act: Activity, permissions: List<String>, task: () -> Unit, rejectCallback: (() -> Unit)?) {
            whenHasAnyPremissionsNotWithRequestPermissions(permissions, {}){
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, permissions,
                        //授权之后的回调函数
                        { _, res ->
                            var result = false
                            for (c in res) {
                                if (c == PackageManager.PERMISSION_GRANTED) {
                                    result = true
                                    break
                                }
                            }
                            if (result) {
                                task()
                            } else {
                                rejectCallback?.also { it() }
                            }
                        }, rejectCallback)
            }
        }

        /**
         * 当APP拥有[permissions]列表中任意一项授权时运行[task]，否则运行[rejectCallback].
         *
         * 函数首先检查[permissions]列表中权限被授予的情况，如果被授予列表中不为空，就执行[task]；
         * 否则，则请求授予[permissions]列表中所有权限，并且在用户授予其中任何一项时，执行[task],如
         * 果用户仍然不授予任何权限，则执行[rejectCallback].
         *
         * @param[act] 调用的Acvitity.
         * @param[permissions] 需要检查的授权列表.
         * @param[task] 拥有任意一项授权时要执行的任务.
         * @param[rejectCallback] 无法获得任意一项授权是执行的任务.
         *
         */
        fun whenHasAnyPremissions(act: Activity, permissions: List<String>, task: () -> Unit, rejectCallback: (() -> Unit)?) {
            whenHasAnyPremissionsNotWithRequestPermissions(permissions, task){
                //只有在Android 6.0下才会有动态申请权限的效果,requestPermissions(...)内有检查,
                //低版本直接调用rejectCallback.
                requestPermissions(act, permissions,
                        //授权之后的回调函数
                        { _, res ->
                            var result = false
                            for (c in res) {
                                if (c == PackageManager.PERMISSION_GRANTED) {
                                    result = true
                                    break
                                }
                            }
                            if (result) {
                                task()
                            } else {
                                rejectCallback?.also { it() }
                            }
                        }, rejectCallback)
            }
        }

        /**
         * 仅在拥有[permissions]所有授权时执行[task]，否则，不请求缺失的授权改为执行[onNoPerms].
         *
         * @param[permissions] 需要的授权列表.
         * @param[task] 拥有完整授权时要执行的任务.
         * @param[onNoPerms] 没有获得完整授权是执行的任务.
         *
         */
        fun whenHasAllPremissionsNotWithRequestPermissions(permissions: List<String>, task: () -> Unit, onNoPerms:((PermissionCheckResult)->Unit)?):PermissionCheckResult {
            return checkPermissions(permissions).also {
                if (it.allPass) {
                    task()
                }else{
                    onNoPerms?.also { callback->callback(it) }
                }
            }
        }
        /**
         * 在拥有[permissions]任意一项授权时执行[task]，否则，不请求缺失的授权改为执行[onNoPerms].
         *
         * @param[permissions] 需要检查的授权列表.
         * @param[task] 拥有任意一项授权时要执行的任务.
         * @param[onNoPerms] 没有获得任意一项授权是执行的任务.
         *
         */
        fun whenHasAnyPremissionsNotWithRequestPermissions(permissions: List<String>, task: () -> Unit, onNoPerms:(()->Unit)?) {
            checkPermissions(permissions).also {
                if (it.passedPermissions.isNotEmpty()) {
                    task()
                }else{
                    onNoPerms?.apply { this() }
                }
            }
        }

    }

}

/**
 * 定位授权工具.
 */
@Suppress("UNUSED")
class LocationPermsUtils {
    companion object {

        val LOCATION_PERMISSIONS = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)

        /**
         * 只要有任何定位权限，就执行[task],否则执行[onRejectGrantCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectGrantCallback] 拒绝授予权限时候的处理方法.
         */
        fun canAccessLocation(act: Activity, task: () -> Unit, onRejectGrantCallback: (() -> Unit)?) {
            PermissionUtils.whenHasAnyPremissions(act, LOCATION_PERMISSIONS, task, onRejectGrantCallback)
        }

    }
}

/**
 * 拨号授权工具.
 */
@Suppress("UNUSED")
class CallPermsUtils {
    companion object {
        /**
         * 拥有[Manifest.permission.CALL_PHONE]权限，就执行[task],否则执行[onRejectGrantCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectGrantCallback] 拒绝授予权限时候的处理方法.
         */
        fun canMakeACall(act: Activity, task: () -> Unit, onRejectGrantCallback: (() -> Unit)?) {
            PermissionUtils.whenHasAnyPremissions(act, listOf(Manifest.permission.CALL_PHONE), task, onRejectGrantCallback)
        }
    }
}


/**
 * 文件读写授权工具.
 */
@Suppress("UNUSED")
class FilePermsUtils {
    companion object {
        /**
         * 同时拥有[Manifest.permission.READ_EXTERNAL_STORAGE]和[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun bothReadWrite(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), task, onRejectCallback)
        }
        /**
         * 拥有[Manifest.permission.READ_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun onlyRead(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.READ_EXTERNAL_STORAGE), task, onRejectCallback)
        }
        /**
         * 拥有[Manifest.permission.WRITE_EXTERNAL_STORAGE]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun onlyWrite(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), task, onRejectCallback)
        }
    }
}

/**
 * 网络授权工具.
 */
@Suppress("UNUSED")
class NetwordPermsUtils {
    companion object {
        /**
         * 拥有[Manifest.permission.INTERNET]权限时，就执行[task],否则执行[onRejectCallback].
         *
         * @param[act] 调用所在的Activity.
         * @param[task] 要执行的任务.
         * @param[onRejectCallback] 拒绝授予权限时候的处理方法.
         */
        fun whenCanAccessInternet(act: Activity, task: () -> Unit, onRejectCallback: () -> Unit) {
            PermissionUtils.whenHasAllPremissions(act, listOf(Manifest.permission.INTERNET), task, onRejectCallback)
        }
    }
}

