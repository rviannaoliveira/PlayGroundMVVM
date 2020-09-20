package com.rviannaoliveira.shared.extensions

import android.app.Activity
import android.content.Intent

fun Activity.requireString(key: String): String {
    return intent.requireString(key)
}

fun Intent.requireString(key: String): String =
    getStringExtra(key)!!
