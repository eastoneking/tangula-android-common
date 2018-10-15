package com.tangula.android.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.widget.Toast

class TaskUtils {

    companion object {

        /**
         * To start a thread in UI thread.
         * <p>This function use AsyncTask to run the task.</p>
         * @param the task.
         */
        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        fun runInUiThread(task:Runnable?){
            object: AsyncTask<Unit, Unit, Unit>(){
                override fun doInBackground(vararg params: Unit?){
                }
                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    task?.run()
                }
            }.execute()
        }

        /**
         * To start a thread in UI thread.
         * <p>This function use AsyncTask to run the task.</p>
         * @param the task.
         */
        @JvmStatic
        fun runInUiThread(task:()->Unit?){
            runInUiThread(Runnable{task?.invoke()})
        }

        /**
         * To start a thread in background.
         * <p>This function use AsyncTask to run the task.</p>
         * @param the task.
         */
        @JvmStatic
        @SuppressLint("StaticFieldLeak")
        fun runInBackground(task: ()->Unit){
            object: AsyncTask<Unit, Unit, Unit>(){
                override fun doInBackground(vararg params: Unit?) {
                    task()
                }
            }.execute()
        }

        /**
         * To start a thread in background.
         * <p>This function use AsyncTask to run the task.</p>
         * @param the task.
         */
        @JvmStatic
        fun runInBackground(task: Runnable?){
            runInBackground{task?.run()}
        }

    }
}