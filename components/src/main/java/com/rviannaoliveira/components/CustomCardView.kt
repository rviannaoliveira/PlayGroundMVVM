package com.rviannaoliveira.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.cardview.widget.CardView
import com.rviannaoliveira.shared.extensions.getPriceFormat
import kotlinx.android.synthetic.main.card_image.view.*

class CustomCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.card_image, this)

        populate(attrs)
    }

    private fun populate(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CardImage,
            0, 0
        ).apply {

            title = getString(R.styleable.CardImage_title)
            subtitle = getString(R.styleable.CardImage_subtitle)
            content = getString(R.styleable.CardImage_content)
            imageResource = getResourceId(R.styleable.CardImage_imageSrc, 0)
        }
    }

    override fun setOnClickListener(l: OnClickListener?) {
        card_container.setOnClickListener(l)
    }

    var title: String? = null
        set(value) {
            field = value
            card_title.text = value?.getPriceFormat(resources)
        }

    @StringRes
    var titleResource: Int = 0
        set(value) {
            field = value
            card_title.setText(value)
        }

    var subtitle: String? = null
        set(value) {
            field = value
            card_subtitle.text = value.getSpace(resources)
        }

    @StringRes
    var subtitleResource: Int = 0
        set(value) {
            field = value
            card_subtitle.setText(value)
        }

    var content: String? = null
        set(value) {
            field = value
            card_content.text = value.getArea(resources)
        }

    @StringRes
    var contentResource: Int = 0
        set(value) {
            field = value
            card_content.setText(value)
        }

    @DrawableRes
    var imageResource: Int = 0
        set(value) {
            field = value
            card_image.setImageResource(value)
        }

    var imageDrawable: Drawable? = null
        set(value) {
            field = value
            card_image.setImageDrawable(value)
        }

    var imageBitmap: Bitmap? = null
        set(value) {
            field = value
            card_image.setImageBitmap(value)
        }
}
private fun String?.getArea(resources: Resources): String? {
    return this?.let { "${resources.getString(R.string.area)} $it m" }
        ?: resources.getString(R.string.empty)
}

private fun String?.getSpace(resources: Resources): String? {
    return this?.let { "${resources.getString(R.string.parking_space)} $it" }
        ?: resources.getString(R.string.empty)
}
