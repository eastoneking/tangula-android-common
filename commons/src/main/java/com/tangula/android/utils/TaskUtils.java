package com.tangula.android.utils;

import android.os.AsyncTask;

public class TaskUtils {

    @SuppressWarnings("unused")
    public static void runInUiThread(final Runnable task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if (task != null) {
                    task.run();
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @SuppressWarnings("unused")
    public static void runInBackground(final Runnable task) {
        runInAsyncPool(task);
    }

    @SuppressWarnings("WeakerAccess")
    public static void runInAsyncPool(final Runnable task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (task != null) {
                    task.run();
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @SuppressWarnings("unused")
    public static void runInSerialExecutor(final Runnable task) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                if (task != null) {
                    task.run();
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR);
    }

}
