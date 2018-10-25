package com.tangula.android.utils

import android.annotation.SuppressLint
import android.os.Build

@Suppress("UNUSED", "MemberVisibilityCanBePrivate")
class SdkVersionDecides {

    companion object {

        val runFuncWhenNullDoNothing = { func: (() -> Unit)? ->
            func?.also {
                it()
            }
        }

        //------------------API 28
        fun beforeSdk28A9d0(): Boolean {
            return Build.VERSION.SDK_INT <= 28
        }

        fun beforeSdk28A9d0(func: (() -> Unit)?) {
            beforeSdk28A9d0(func, null)
        }

        fun beforeSdk28A9d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk28A9d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk28A9d0(): Boolean {
            return Build.VERSION.SDK_INT >= 28
        }

        fun afterSdk28A9d0(func: (() -> Unit)?) {
            afterSdk28A9d0(func, null)
        }

        fun afterSdk28A9d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk28A9d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //--------------------API 27
        fun beforeSdk27A8d1(): Boolean {
            return Build.VERSION.SDK_INT <= 27
        }

        fun beforeSdk27A8d1(func: (() -> Unit)?) {
            beforeSdk27A8d1(func, null)
        }

        fun beforeSdk27A8d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk27A8d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk27A8d1(): Boolean {
            return Build.VERSION.SDK_INT >= 27
        }

        fun afterSdk27A8d1(func: (() -> Unit)?) {
            afterSdk27A8d1(func, null)
        }

        fun afterSdk27A8d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk27A8d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 26

        fun beforeSdk26A8d0(): Boolean {
            return Build.VERSION.SDK_INT <= 26
        }

        fun beforeSdk26A8d0(func: (() -> Unit)?) {
            beforeSdk26A8d0(func, null)
        }

        fun beforeSdk26A8d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk26A8d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk26A8d0(): Boolean {
            return Build.VERSION.SDK_INT >= 26
        }

        fun afterSdk26A8d0(func: (() -> Unit)?) {
            afterSdk26A8d0(func, null)
        }

        fun afterSdk26A8d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk26A8d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 25

        fun beforeSdk25A7d1d1(): Boolean {
            return Build.VERSION.SDK_INT <= 25
        }

        fun beforeSdk25A7d1d1(func: (() -> Unit)?) {
            beforeSdk25A7d1d1(func, null)
        }

        fun beforeSdk25A7d1d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk25A7d1d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk25A7d1d1(): Boolean {
            return Build.VERSION.SDK_INT >= 25
        }

        fun afterSdk25A7d1d1(func: (() -> Unit)?) {
            afterSdk25A7d1d1(func, null)
        }

        fun afterSdk25A7d1d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk25A7d1d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }
        //----------------------API 24

        fun beforeSdk24A7d0(): Boolean {
            return Build.VERSION.SDK_INT <= 24
        }

        fun beforeSdk24A7d0(func: (() -> Unit)?) {
            beforeSdk24A7d0(func, null)
        }

        fun beforeSdk24A7d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk24A7d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk24A7d0(): Boolean {
            return Build.VERSION.SDK_INT >= 24
        }

        fun afterSdk24A7d0(func: (() -> Unit)?) {
            afterSdk24A7d0(func, null)
        }

        fun afterSdk24A7d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk24A7d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 23
        fun beforeSdk23A6d0(): Boolean {
            return Build.VERSION.SDK_INT <= 23
        }

        fun beforeSdk23A6d0(func: (() -> Unit)?) {
            beforeSdk23A6d0(func, null)
        }

        fun beforeSdk23A6d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk23A6d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk23A6d0(): Boolean {
            return Build.VERSION.SDK_INT >= 23
        }

        fun afterSdk23A6d0(func: (() -> Unit)?) {
            afterSdk23A6d0(func, null)
        }


        fun afterSdk23A6d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk23A6d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 22

        fun beforeSdk22A5d1(): Boolean {
            return Build.VERSION.SDK_INT <= 22
        }

        fun beforeSdk22A5d1(func: (() -> Unit)?) {
            beforeSdk22A5d1(func, null)
        }


        fun beforeSdk22A5d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk22A5d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk22A5d1(): Boolean {
            return Build.VERSION.SDK_INT >= 22
        }

        fun afterSdk22A5d1(func: (() -> Unit)?) {
            afterSdk22A5d1(func, null)
        }

        fun afterSdk22A5d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk22A5d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 21

        fun beforeSdk21A5d0(): Boolean {
            return Build.VERSION.SDK_INT <= 21
        }

        fun beforeSdk21A5d0(func: (() -> Unit)?) {
            beforeSdk21A5d0(func, null)
        }

        fun beforeSdk21A5d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk21A5d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk21A5d0(): Boolean {
            return Build.VERSION.SDK_INT >= 21
        }

        fun afterSdk21A5d0(func: (() -> Unit)?) {
            afterSdk21A5d0(func, null)
        }

        fun afterSdk21A5d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk21A5d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 20


        fun beforeSdk20A4d4W(): Boolean {
            return Build.VERSION.SDK_INT <= 20
        }

        fun beforeSdk20A4d4W(func: (() -> Unit)?) {
            beforeSdk20A4d4W(func, null)
        }

        fun beforeSdk20A4d4W(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk20A4d4W()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk20A4d4W(): Boolean {
            return Build.VERSION.SDK_INT >= 20
        }

        fun afterSdk20A4d4W(func: (() -> Unit)?) {
            afterSdk20A4d4W(func, null)
        }

        fun afterSdk20A4d4W(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk20A4d4W()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 19

        fun beforeSdk19A4d4(): Boolean {
            return Build.VERSION.SDK_INT <= 19
        }

        fun beforeSdk19A4d4(func: (() -> Unit)?) {
            beforeSdk19A4d4(func, null)
        }

        fun beforeSdk19A4d4(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk19A4d4()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk19A4d4(): Boolean {
            return Build.VERSION.SDK_INT >= 19
        }

        fun afterSdk19A4d4(func: (() -> Unit)?) {
            afterSdk19A4d4(func, null)
        }

        fun afterSdk19A4d4(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk19A4d4()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 18
        fun beforeSdk18A4d3(): Boolean {
            return Build.VERSION.SDK_INT <= 18
        }

        fun beforeSdk18A4d3(func: (() -> Unit)?) {
            beforeSdk18A4d3(func, null)
        }

        fun beforeSdk18A4d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk18A4d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk18A4d3(): Boolean {
            return Build.VERSION.SDK_INT >= 18
        }

        fun afterSdk18A4d3(func: (() -> Unit)?) {
            afterSdk18A4d3(func, null)
        }

        fun afterSdk18A4d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk18A4d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 17

        fun beforeSdk17A4d2(): Boolean {
            return Build.VERSION.SDK_INT <= 17
        }

        fun beforeSdk17A4d2(func: (() -> Unit)?) {
            beforeSdk17A4d2(func, null)
        }

        fun beforeSdk17A4d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk17A4d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk17A4d2(): Boolean {
            return Build.VERSION.SDK_INT >= 17
        }

        fun afterSdk17A4d2(func: (() -> Unit)?) {
            afterSdk17A4d2(func, null)
        }

        fun afterSdk17A4d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk17A4d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }
        //----------------------API 16

        fun beforeSdk16A4d1(): Boolean {
            return Build.VERSION.SDK_INT <= 16
        }

        fun beforeSdk16A4d1(func: (() -> Unit)?) {
            beforeSdk16A4d1(func, null)
        }

        fun beforeSdk16A4d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk16A4d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        fun afterSdk16A4d1(): Boolean {
            return Build.VERSION.SDK_INT >= 16
        }

        fun afterSdk16A4d1(func: (() -> Unit)?) {
            afterSdk16A4d1(func, null)
        }

        fun afterSdk16A4d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk16A4d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }
        //----------------------API 15

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk15A4d0d3(): Boolean {
            return Build.VERSION.SDK_INT <= 15
        }

        fun beforeSdk15A4d0d3(func: (() -> Unit)?) {
            beforeSdk15A4d0d3(func, null)
        }

        fun beforeSdk15A4d0d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk15A4d0d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk15A4d0d3(): Boolean {
            return Build.VERSION.SDK_INT >= 15
        }

        fun afterSdk15A4d0d3(func: (() -> Unit)?) {
            afterSdk15A4d0d3(func, null)
        }

        fun afterSdk15A4d0d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk15A4d0d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        //----------------------API 14
        fun beforeSdk14A4d0(): Boolean {
            return Build.VERSION.SDK_INT <= 14
        }

        fun beforeSdk14A4d0(func: (() -> Unit)?) {
            beforeSdk14A4d0(func, null)
        }

        fun beforeSdk14A4d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk14A4d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk14A4d0(): Boolean {
            return Build.VERSION.SDK_INT >= 14
        }

        fun afterSdk14A4d0(func: (() -> Unit)?) {
            afterSdk14A4d0(func, null)
        }

        fun afterSdk14A4d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk14A4d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 13

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk12A3d2(): Boolean {
            return Build.VERSION.SDK_INT <= 13
        }

        fun beforeSdk12A3d2(func: (() -> Unit)?) {
            beforeSdk12A3d2(func, null)
        }

        fun beforeSdk12A3d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk12A3d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk12A3d2(): Boolean {
            return Build.VERSION.SDK_INT >= 13
        }

        fun afterSdk12A3d2(func: (() -> Unit)?) {
            afterSdk12A3d2(func, null)
        }

        fun afterSdk12A3d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk12A3d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 12

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk12A3d1(): Boolean {
            return Build.VERSION.SDK_INT <= 12
        }

        fun beforeSdk12A3d1(func: (() -> Unit)?) {
            beforeSdk12A3d1(func, null)
        }

        fun beforeSdk12A3d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk12A3d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk12A3d1(): Boolean {
            return Build.VERSION.SDK_INT >= 12
        }

        fun afterSdk12A3d1(func: (() -> Unit)?) {
            afterSdk12A3d1(func, null)
        }

        fun afterSdk12A3d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk12A3d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 11
        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk11A3d0(): Boolean {
            return Build.VERSION.SDK_INT <= 11
        }

        fun beforeSdk11A3d0(func: (() -> Unit)?) {
            beforeSdk11A3d0(func, null)
        }

        fun beforeSdk11A3d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk11A3d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk11A3d0(): Boolean {
            return Build.VERSION.SDK_INT >= 11
        }

        fun afterSdk11A3d0(func: (() -> Unit)?) {
            afterSdk11A3d0(func, null)
        }

        fun afterSdk11A3d0(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk11A3d0()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 10

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk10A2d3d3(): Boolean {
            return Build.VERSION.SDK_INT <= 10
        }

        fun beforeSdk10A2d3d3(func: (() -> Unit)?) {
            beforeSdk10A2d3d3(func, null)
        }

        fun beforeSdk10A2d3d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk10A2d3d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk10A2d3d3(): Boolean {
            return Build.VERSION.SDK_INT >= 10
        }

        fun afterSdk10A2d3d3(func: (() -> Unit)?) {
            afterSdk10A2d3d3(func, null)
        }

        fun afterSdk10A2d3d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk10A2d3d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 9

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk9A2d3(): Boolean {
            return Build.VERSION.SDK_INT <= 9
        }

        fun beforeSdk9A2d3(func: (() -> Unit)?) {
            beforeSdk9A2d3(func, null)
        }

        fun beforeSdk9A2d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk9A2d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk9A2d3(): Boolean {
            return Build.VERSION.SDK_INT >= 9
        }

        fun afterSdk9A2d3(func: (() -> Unit)?) {
            afterSdk9A2d3(func, null)
        }

        fun afterSdk9A2d3(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk9A2d3()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }


        //----------------------API 8

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk8A2d2(): Boolean {
            return Build.VERSION.SDK_INT <= 8
        }

        fun beforeSdk8A2d2(func: (() -> Unit)?) {
            beforeSdk8A2d2(func, null)
        }

        fun beforeSdk8A2d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk8A2d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk8A2d2(): Boolean {
            return Build.VERSION.SDK_INT >= 8
        }

        fun afterSdk8A2d2(func: (() -> Unit)?) {
            afterSdk8A2d2(func, null)
        }

        fun afterSdk8A2d2(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk8A2d2()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        //----------------------API 7

        @SuppressLint("ObsoleteSdkInt")
        fun beforeSdk7A2d1(): Boolean {
            return Build.VERSION.SDK_INT <= 7
        }

        fun beforeSdk7A2d1(func: (() -> Unit)?) {
            beforeSdk7A2d1(func, null)
        }

        fun beforeSdk7A2d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (beforeSdk7A2d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

        @SuppressLint("ObsoleteSdkInt")
        fun afterSdk7A2d1(): Boolean {
            return Build.VERSION.SDK_INT >= 7
        }

        fun afterSdk7A2d1(func: (() -> Unit)?) {
            afterSdk7A2d1(func, null)
        }

        fun afterSdk7A2d1(func: (() -> Unit)?, onNot: (() -> Unit)?) {
            if (afterSdk7A2d1()) {
                runFuncWhenNullDoNothing(func)
            } else {
                onNot?.apply { this() }
            }
        }

    }

}