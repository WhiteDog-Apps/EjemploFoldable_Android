package dev.whitedog.ejemplofoldable.utils.size_converter

import android.content.Context
import android.util.TypedValue

object SizeConverter {

    fun spToPx(sp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics).toInt()
    }

    fun pxToSp(px: Float, context: Context): Float {
        return px / context.resources.displayMetrics.scaledDensity
    }

    fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

}