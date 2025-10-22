package com.github.dragon925.ram.utils

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.viewbinding.ViewBinding

inline fun ViewBinding.withBindingContext(
    block: Context.() -> Unit
) {
    root.context.block()
}
fun Context.getThemedColor(@AttrRes id: Int): Int {
    val typedValue = TypedValue()
    return if (theme.resolveAttribute(id, typedValue, true)) {
        typedValue.data
    } else {
        Color.TRANSPARENT
    }
}