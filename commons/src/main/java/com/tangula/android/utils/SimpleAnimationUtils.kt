package com.tangula.android.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

class SimpleAnimationUtils {

    companion object {


        fun playScaleAnimation(view: View, fromX:Float, toX:Float, fromY:Float, toY:Float,
                               pivotXType:Int, pivotXValue:Float, pivotYType:Int, pivotYValue:Float, duration:Long):ScaleAnimation{
            return  playScaleAnimation(view, fromX, toX, fromY, toY,
                                   pivotXType, pivotXValue, pivotYType, pivotYValue, null, duration, null,null)
        }

        fun playScaleAnimation(view: View, fromX:Float, toX:Float, fromY:Float, toY:Float,
                               pivotXType:Int, pivotXValue:Float, pivotYType:Int, pivotYValue:Float, startOffset:Long, duration:Long):ScaleAnimation{
            return  playScaleAnimation(view, fromX, toX, fromY, toY,
                    pivotXType, pivotXValue, pivotYType, pivotYValue, startOffset, duration, null,null)
        }

        fun playScaleAnimation(view: View, fromX:Float, toX:Float, fromY:Float, toY:Float,
                               pivotXType:Int, pivotXValue:Float, pivotYType:Int, pivotYValue:Float, startOffset:Long?, duration:Long, repeatCount:Int?, repeatMode:Int?):ScaleAnimation{
            val scaleAnimation =
                    ScaleAnimation(fromX, fromX, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue)
            startOffset?.also{scaleAnimation.startOffset = startOffset}
            scaleAnimation.duration = duration
            repeatCount?.also { scaleAnimation.repeatCount = repeatCount }
            repeatMode?.also { scaleAnimation.repeatMode =repeatMode  }
            view.animation = scaleAnimation
            scaleAnimation.startNow()
            return scaleAnimation
        }



    }
}