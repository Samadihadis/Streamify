package com.samadihadis.streamify.utils

import android.view.View

/**
 * Change view visibility with state
 */

fun View.visibilityState(isShow: Boolean) {
    if (isShow) visible() else gone()
}

/**
 * Make View visible
 */

fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * Makes View gone
 */

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}
