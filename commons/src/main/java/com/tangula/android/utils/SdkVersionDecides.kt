package com.tangula.android.utils

import android.os.Build

class SdkVersionDecides {

    companion object {

        val runFuncWhenNullDoNothing = {func:(()->Unit)?->
                func?.also {
                    it()
                }
        }

        fun afterSdk28A9d0():Boolean{
            return Build.VERSION.SDK_INT >= 28
        }

        fun afterSdk28A9d0(func:(()->Unit)?){
            if(afterSdk28A9d0()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk27A8d1():Boolean{
            return Build.VERSION.SDK_INT >= 27
        }

        fun afterSdk27A8d1(func:(()->Unit)?){
            if(afterSdk27A8d1()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk26A8d0():Boolean{
            return Build.VERSION.SDK_INT >= 26
        }

        fun afterSdk26A8d0(func:(()->Unit)?){
            if(afterSdk26A8d0()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk25A7d1d1():Boolean{
            return Build.VERSION.SDK_INT >= 25
        }

        fun afterSdk25A7d1d1(func:(()->Unit)?){
            if(afterSdk25A7d1d1()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk24A7d0():Boolean{
            return Build.VERSION.SDK_INT >= 24
        }

        fun afterSdk24A7d0(func:(()->Unit)?){
            if(afterSdk24A7d0()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk23A6d0():Boolean{
            return Build.VERSION.SDK_INT >= 23
        }

        fun afterSdk23A6d0(func:(()->Unit)?){
            if(afterSdk23A6d0()){
                runFuncWhenNullDoNothing(func)
            }
        }



        fun afterSdk22A5d1():Boolean{
            return Build.VERSION.SDK_INT >= 22
        }

        fun afterSdk22A5d1(func:(()->Unit)?){
            if(afterSdk22A5d1()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk21A5d0():Boolean{
            return Build.VERSION.SDK_INT >= 21
        }

        fun afterSdk21A5d0(func:(()->Unit)?){
            if(afterSdk21A5d0()){
                runFuncWhenNullDoNothing(func)
            }
        }



        fun afterSdk20A4d4W():Boolean{
            return Build.VERSION.SDK_INT >= 20
        }

        fun afterSdk20A4d4W(func:(()->Unit)?){
            if(afterSdk20A4d4W()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk19A4d4():Boolean{
            return Build.VERSION.SDK_INT >= 19
        }

        fun afterSdk19A4d4(func:(()->Unit)?){
            if(afterSdk19A4d4()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk18A4d3():Boolean{
            return Build.VERSION.SDK_INT >= 18
        }

        fun afterSdk18A4d3(func:(()->Unit)?){
            if(afterSdk18A4d3()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk17A4d2():Boolean{
            return Build.VERSION.SDK_INT >= 17
        }

        fun afterSdk17A4d2(func:(()->Unit)?){
            if(afterSdk17A4d2()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk16A4d1():Boolean{
            return Build.VERSION.SDK_INT >= 16
        }

        fun afterSdk16A4d1(func:(()->Unit)?){
            if(afterSdk16A4d1()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk15A4d0d3():Boolean{
            return Build.VERSION.SDK_INT >= 15
        }

        fun afterSdk15A4d0d3(func:(()->Unit)?){
            if(afterSdk15A4d0d3()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk14A4d0():Boolean{
            return Build.VERSION.SDK_INT >= 14
        }

        fun afterSdk14A4d0(func:(()->Unit)?){
            if(afterSdk14A4d0()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk12A3d2():Boolean{
            return Build.VERSION.SDK_INT >= 13
        }

        fun afterSdk12A3d2(func:(()->Unit)?){
            if(afterSdk12A3d2()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk12A3d1():Boolean{
            return Build.VERSION.SDK_INT >= 12
        }

        fun afterSdk12A3d1(func:(()->Unit)?){
            if(afterSdk12A3d1()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk11A3d0():Boolean{
            return Build.VERSION.SDK_INT >= 11
        }

        fun afterSdk11A3d0(func:(()->Unit)?){
            if(afterSdk11A3d0()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk10A2d3d3():Boolean{
            return Build.VERSION.SDK_INT >= 10
        }

        fun afterSdk10A2d3d3(func:(()->Unit)?){
            if(afterSdk10A2d3d3()){
                runFuncWhenNullDoNothing(func)
            }
        }

        fun afterSdk9A2d3():Boolean{
            return Build.VERSION.SDK_INT >= 9
        }

        fun afterSdk9A2d3(func:(()->Unit)?){
            if(afterSdk9A2d3()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk8A2d2():Boolean{
            return Build.VERSION.SDK_INT >= 8
        }

        fun afterSdk8A2d2(func:(()->Unit)?){
            if(afterSdk8A2d2()){
                runFuncWhenNullDoNothing(func)
            }
        }


        fun afterSdk7A2d1():Boolean{
            return Build.VERSION.SDK_INT >= 7
        }

        fun afterSdk7A2d1(func:(()->Unit)?){
            if(afterSdk7A2d1()){
                runFuncWhenNullDoNothing(func)
            }
        }

    }

}