package com.rviannaoliveira.shared.extensions

import android.content.res.Resources
import com.rviannaoliveira.shared.R
import java.text.NumberFormat
import java.util.*

fun String?.getPriceFormat(resources: Resources): String? {
    val format: NumberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    return this?.let { format.format(Integer.valueOf(it)) }
        ?: resources.getString(R.string.empty)
}
