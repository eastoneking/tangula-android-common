package com.tangula.android.utils

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.widget.Toast

/**
 * Android's Thread Utils.
 * <p>
 *     <ul>
 *         <li>run in background thread.</li>
 *         <li>run in ui thread.</li>
 *         <li>show troast in ui thread.</li>
 *     </ul>
 * </p>
 */
class UiThreadUtils {

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

        /**
         * Show text as toast with special duration.
         * @param text The messasge would show in toast.
         * @param duration The display time length.
         */
        @JvmStatic
        fun showToast(text:String, duration:Int){
            runInUiThread{ Toast.makeText(ApplicationUtils.APP, text, duration).show()}
        }

        /**
         * Show text as toast long time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastLong(text:String){
            showToast(text, Toast.LENGTH_LONG)
        }

        /**
         * Show text as toast short time.
         * @param text The messasge would show in toast.
         */
        @JvmStatic
        fun showToastShort(text:String){
            showToast(text, Toast.LENGTH_SHORT)
        }

    }

}