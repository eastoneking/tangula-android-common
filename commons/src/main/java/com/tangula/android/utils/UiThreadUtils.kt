package com.tangula.android.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.widget.Toast

class UiThreadUtils {

    companion object {
        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        fun runInUiThread(task:Runnable){
            object: AsyncTask<Unit, Unit, Unit>(){
                override fun doInBackground(vararg params: Unit?) {
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    task.run()
                }
            }.execute()
        }

        fun runInUiThread(task:()->Unit){
            runInUiThread(Runnable{task.invoke()})
        }


        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        fun runInBackground(task: ()->Unit){
            object: AsyncTask<Unit, Unit, Unit>(){
                override fun doInBackground(vararg params: Unit?) {
                    task()
                }
            }.execute()
        }


        @JvmStatic
        fun runInBackground(task: Runnable){
           runInBackground{task.run()}
        }


        @JvmStatic
        fun showToast(text:String, duration:Int){
            runInUiThread{ Toast.makeText(ApplicationUtils.APP, text, duration).show()}
        }

        @JvmStatic
        fun showToastLong(text:String){
            showToast(text, Toast.LENGTH_LONG)
        }

        @JvmStatic
        fun showToastShort(text:String){
            showToast(text, Toast.LENGTH_SHORT)
        }

    }

}