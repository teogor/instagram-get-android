package com.dolphpire.api.initializer;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@KeepForApi
public final class Preconditions {
    @KeepForApi
    @NonNull
    public static <T> T checkNotNull(@Nullable T var0) {
        if (var0 == null) {
            throw new NullPointerException("null reference");
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static String checkNotEmpty(String var0) {
        if (TextUtils.isEmpty(var0)) {
            throw new IllegalArgumentException("Given String is empty or null");
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static String checkNotEmpty(String var0, Object var1) {
        if (TextUtils.isEmpty(var0)) {
            throw new IllegalArgumentException(String.valueOf(var1));
        } else {
            return var0;
        }
    }

    @KeepForApi
    @NonNull
    public static <T> T checkNotNull(T var0, Object var1) {
        if (var0 == null) {
            throw new NullPointerException(String.valueOf(var1));
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static int checkNotZero(int var0, Object var1) {
        if (var0 == 0) {
            throw new IllegalArgumentException(String.valueOf(var1));
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static int checkNotZero(int var0) {
        if (var0 == 0) {
            throw new IllegalArgumentException("Given Integer is zero");
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static long checkNotZero(long var0, Object var2) {
        if (var0 == 0L) {
            throw new IllegalArgumentException(String.valueOf(var2));
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static long checkNotZero(long var0) {
        if (var0 == 0L) {
            throw new IllegalArgumentException("Given Long is zero");
        } else {
            return var0;
        }
    }

    @KeepForApi
    public static void checkState(boolean var0) {
        if (!var0) {
            throw new IllegalStateException();
        }
    }

    @KeepForApi
    public static void checkState(boolean var0, Object var1) {
        if (!var0) {
            throw new IllegalStateException(String.valueOf(var1));
        }
    }

    @KeepForApi
    public static void checkState(boolean var0, String var1, Object... var2) {
        if (!var0) {
            throw new IllegalStateException(String.format(var1, var2));
        }
    }

    @KeepForApi
    public static void checkArgument(boolean var0, Object var1) {
        if (!var0) {
            throw new IllegalArgumentException(String.valueOf(var1));
        }
    }

    @KeepForApi
    public static void checkArgument(boolean var0, String var1, Object... var2) {
        if (!var0) {
            throw new IllegalArgumentException(String.format(var1, var2));
        }
    }

    @KeepForApi
    public static void checkArgument(boolean var0) {
        if (!var0) {
            throw new IllegalArgumentException();
        }
    }

    private Preconditions() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForApi
    public static void checkMainThread(String var0) {
        if (!isMainThread()) {
            throw new IllegalStateException(var0);
        }
    }

    @KeepForApi
    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    @KeepForApi
    public static void checkNotMainThread(String var0) {
        if (isMainThread()) {
            throw new IllegalStateException(var0);
        }
    }

    @KeepForApi
    public static void checkHandlerThread(Handler var0) {
        checkHandlerThread(var0, "Must be called on the handler thread");
    }

    @KeepForApi
    public static void checkHandlerThread(Handler var0, String var1) {
        if (Looper.myLooper() != var0.getLooper()) {
            throw new IllegalStateException(var1);
        }
    }

    private static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}