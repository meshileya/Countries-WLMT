package com.example.countries_wlmt.common

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * make view visible
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * make view visible, if onlyInvisible is true, the view takes up the assigned space and not appear
 * other wise, it is gone totally
 */
fun View.hide(onlyInvisible: Boolean = false) {
    visibility = if (onlyInvisible) {
        View.INVISIBLE
    } else {
        View.GONE
    }
}

fun Context.toast(message: String, length: Int = Toast.LENGTH_LONG) =
    Toast.makeText(this, message, length).show()