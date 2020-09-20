package com.rviannaoliveira.shared

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter{
    @BindingAdapter("visible")
    @JvmStatic
    fun setVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}
