package com.tangula.android.utils;

import android.Manifest;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.graphics.drawable.DrawableCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

@SuppressWarnings("unused")
public class Res {

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void readFromInputStream(
            Producer<InputStream> fac,
            Consumer<InputStream> callback,
            Consumer<Throwable> onThrow
    ) {
        InputStream is = null;
        try {
            is = fac.product();
            if (callback != null) {
                callback.accept(is);
            }
        } catch (Throwable ex) {
            if (onThrow != null) {
                try {
                    onThrow.accept(ex);
                } catch (Exception e) {
                    LogUt.e(ex);
                }
            } else {
                LogUt.e(ex);
            }
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable ex) {
                    LogUt.e(ex);
                }
            }
        }
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void writeToOutputStream(
            Producer<OutputStream> fac,
            Consumer<OutputStream> callback,
            Consumer<Throwable> onThrow) {
        OutputStream os = null;
        try {
            os = fac.product();
            if (callback != null) {
                callback.accept(os);
            }
        } catch (Throwable ex) {
            if (onThrow != null) {
                try {
                    onThrow.accept(ex);
                } catch (Exception e) {
                    LogUt.e(e);
                }
            } else {
                LogUt.e(ex);
            }
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Throwable ex) {
                    LogUt.e(ex);
                }
            }
        }
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void readAssertFile(final String filename, Consumer<InputStream> callback, final Consumer<Throwable> onThrow) {
        readFromInputStream(new Producer<InputStream>() {
            @Override
            public InputStream product() {
                try {
                    return ApplicationUtils.APP.getResources().getAssets().open(filename);
                } catch (IOException e) {
                    if (onThrow != null) {
                        try {
                            onThrow.accept(e);
                        } catch (Exception ex) {
                            LogUt.e(ex);
                        }
                    } else {
                        LogUt.e(e);
                    }
                }
                return null;
            }
        }, callback, onThrow);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void readRawFile(final @RawRes int resId, Consumer<InputStream> callback, final Consumer<Throwable> onThrow) {
        readFromInputStream(new Producer<InputStream>() {
            @Override
            public InputStream product() {
                try {
                    return ApplicationUtils.APP.getResources().openRawResource(resId);
                } catch (Throwable e) {
                    if (onThrow != null) {
                        try {
                            onThrow.accept(e);
                        } catch (Exception ex) {
                            LogUt.e(ex);
                        }
                    } else {
                        LogUt.e(e);
                    }
                }
                return null;
            }
        }, callback, onThrow);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void readInteralFile(final String filepath, Consumer<InputStream> callback, final Consumer<Throwable> onThrow) {
        readFromInputStream(new Producer<InputStream>() {
            @Override
            public InputStream product() {
                try {
                    return ApplicationUtils.APP.openFileInput(filepath);
                } catch (Throwable e) {
                    if (onThrow != null) {
                        try {
                            onThrow.accept(e);
                        } catch (Exception ex) {
                            LogUt.e(ex);
                        }
                    } else {
                        LogUt.e(e);
                    }
                }
                return null;
            }
        }, callback, onThrow);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static void writeInteralFile(final String filepath, final boolean isAppend, Consumer<OutputStream> callback, final Consumer<Throwable> onThrow) {
        writeToOutputStream(

                new Producer<OutputStream>() {
                    @Override
                    public OutputStream product() {
                        try {
                            return ApplicationUtils.APP.openFileOutput(filepath, isAppend ? MODE_APPEND : MODE_PRIVATE);
                        } catch (Throwable e) {
                            if (onThrow != null) {
                                try {
                                    onThrow.accept(e);
                                } catch (Exception ex) {
                                    LogUt.e(ex);
                                }
                            } else {
                                LogUt.e(e);
                            }
                        }
                        return null;
                    }
                }, callback, onThrow);
    }

    @SuppressWarnings({"unused", "WeakerAccess"})
    public static File checkSdCardFileParentPathAndCreateIt(String filepath) throws FileNotFoundException {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard, filepath);
        File parent = file.getParentFile();
        if (!parent.exists()) {
            if (!parent.mkdirs()) {
                throw new FileNotFoundException("can not create the file " + parent.getPath());
            }
        }
        return file;
    }

    private static Function<String, FileInputStream> openSdCardInputStream = new Function<String, FileInputStream>() {
        @Override
        public FileInputStream apply(String filepath) throws FileNotFoundException {
            return new FileInputStream(checkSdCardFileParentPathAndCreateIt(filepath));
        }
    };

    private static BiFunction<String, Boolean, FileOutputStream> openSdCardOutput = new BiFunction<String, Boolean, FileOutputStream>() {
        @Override
        public FileOutputStream apply(String filepath, Boolean isAppend) throws Exception {
            return new FileOutputStream(checkSdCardFileParentPathAndCreateIt(filepath), isAppend);
        }
    };

    @SafeVarargs
    @SuppressWarnings({"unused", "WeakerAccess"})
    public static <T> List<T> listOf(T... eles) {
        List<T> res = new ArrayList<>();
        Collections.addAll(res, eles);
        return res;
    }

    @SuppressWarnings("unused")
    public static void readSdCardFile(final String filepath, final Consumer<InputStream> callback, final Consumer<PermissionCheckResult> onNoPerms, final Consumer<Throwable> onThrow) {

        PermissionUtils.whenHasAllPremissionsNotWithRequestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                new Runnable() {
                    @Override
                    public void run() {
                        readFromInputStream(new Producer<InputStream>() {
                            @Override
                            public InputStream product() {
                                try {
                                    return openSdCardInputStream.apply(filepath);
                                } catch (Exception e) {
                                    LogUt.e(e);
                                    return null;
                                }
                            }
                        }, callback, onThrow);
                    }
                }, onNoPerms);

    }


    @SuppressWarnings("unused")
    public static void readSdCardFile(Activity activity, final String filepath, final Consumer<InputStream> callback, final @Nullable Consumer<Throwable> onThrow) {
        PermissionUtils.whenHasAllPremissions(activity, listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                new Runnable() {
                    @Override
                    public void run() {
                        readFromInputStream(new Producer<InputStream>() {
                            @Override
                            public InputStream product() {
                                try {
                                    return openSdCardInputStream.apply(filepath);
                                } catch (Exception e) {
                                    LogUt.e(e);
                                    return null;
                                }
                            }
                        }, callback, onThrow);
                    }
                }, null);
    }

    @SuppressWarnings("unused")
    public static void writeSdCardFile(final String filepath, final boolean isAppend, final Consumer<OutputStream> callback, final Consumer<Throwable> onThrow) {
        PermissionUtils.whenHasAllPremissionsNotWithRequestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                new Runnable() {
                    @Override
                    public void run() {
                        writeToOutputStream(new Producer<OutputStream>() {
                            @Override
                            public OutputStream product() {
                                try {
                                    return openSdCardOutput.apply(filepath, isAppend);
                                } catch (Exception e) {
                                    LogUt.e(e);
                                    return null;
                                }
                            }
                        }, callback, onThrow);
                    }
                }, null);

    }

    @SuppressWarnings("unused")
    public static void writeSdCardFile(final Activity activity, final String filepath, final boolean isAppend, final Consumer<OutputStream> callback, final Consumer<Throwable> onThrow)

    {
        PermissionUtils.whenHasAllPremissions(activity, listOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                new Runnable() {
                    @Override
                    public void run() {
                        writeToOutputStream(new Producer<OutputStream>() {
                            @Override
                            public OutputStream product() {
                                try {
                                    return openSdCardOutput.apply(filepath, isAppend);
                                } catch (Exception e) {
                                    LogUt.e(e);
                                    return null;
                                }
                            }
                        }, callback, onThrow);
                    }
                }, null);
    }


    @SuppressWarnings("unused")
    public static String fetchResString(@StringRes int resId)

    {
        return ApplicationUtils.APP.getString(resId);
    }

    @SuppressWarnings("unused")
    public static String fetchResString(@StringRes int resId, List<String> args) {
        return ApplicationUtils.APP.getString(resId, args);
    }

    @SuppressWarnings("unused")
    public static String[] fetchResStringArray(@ArrayRes int resId)

    {
        return ApplicationUtils.APP.getResources().getStringArray(resId);
    }

    @SuppressWarnings("unused")
    public static int fetchResColor(@ColorRes int colorId) {
        return ApplicationUtils.APP.getResources().getColor(colorId);
    }


    @SuppressWarnings({"unused", "deprecation"})
    public static int fetchResColor(@ColorRes int colorId, Resources.Theme theme) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return ApplicationUtils.APP.getResources().getColor(colorId, theme);
        } else {
            return ApplicationUtils.APP.getResources().getColor(colorId);
        }
    }


    @SuppressWarnings("unused")
    public static int fetchResInt(@IntegerRes int resId) {
        return ApplicationUtils.APP.getResources().getInteger(resId);
    }


    @SuppressWarnings("unused")
    public static int[] fetchResIntArray(@ArrayRes int resId)

    {
        return ApplicationUtils.APP.getResources().getIntArray(resId);
    }


    @SuppressWarnings("unused")
    public static Drawable fetchDrawable(int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ApplicationUtils.APP.getResources().getDrawable(resId, null);
        } else {
            Bitmap btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.getResources(), resId);
            return new BitmapDrawable(ApplicationUtils.APP.getResources(), btmp);
        }

    }

    @SuppressWarnings("unused")
    public static Drawable fetchDrawable(int resId, Resources.Theme theme) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return ApplicationUtils.APP.getResources().getDrawable(resId, theme);
        } else {
            Bitmap btmp = BitmapFactory.decodeResource(ApplicationUtils.APP.getResources(), resId);
            BitmapDrawable res = new BitmapDrawable(ApplicationUtils.APP.getResources(), btmp);
            if (theme != null) {
                DrawableCompat.applyTheme(res, theme);
            }
            return res;
        }
    }
}
