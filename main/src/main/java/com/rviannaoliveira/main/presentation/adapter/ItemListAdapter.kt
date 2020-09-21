package com.rviannaoliveira.main.presentation.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rviannaoliveira.components.CustomCardView
import com.rviannaoliveira.main.R
import com.rviannaoliveira.networking.domain.model.Item
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class ItemListAdapter(
    list: List<Item> = emptyList(),
    private val onClicked: (String) -> (Unit)
) : RecyclerView.Adapter<ItemListViewHolder>() {

    var list: List<Item> = list
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemListViewHolder {
        context = parent.context

        return ItemListViewHolder(
            CustomCardView(
                parent.context
            ).apply {
                val marginLP = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                )
                marginLP.marginEnd =
                    parent.context.resources.getDimension(R.dimen.margin_medium).toInt()
                marginLP.marginStart =
                    parent.context.resources.getDimension(R.dimen.margin_large).toInt()
                marginLP.topMargin =
                    parent.context.resources.getDimension(R.dimen.margin_medium).toInt()
                layoutParams = marginLP
            }
        )
    }

    override fun getItemCount(): Int =
        list.size

    override fun onBindViewHolder(holder: ItemListViewHolder, position: Int) {
        val item = list[position]

        holder.card.title = item.pricingInfos.price.toString()
        holder.card.content = item.usableAreas
        holder.card.subtitle = item.parkingSpaces

        Picasso.get()
            .load(item.images.first())
            .placeholder(context!!.getDrawable(R.drawable.empty)!!)
            .into(object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                holder.card.imageDrawable = placeHolderDrawable
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                holder.card.imageDrawable = errorDrawable
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                holder.card.imageBitmap = bitmap
            }
        })

        holder.card.setOnClickListener {
            onClicked(item.id)
        }
    }
}

class ItemListViewHolder(val card: CustomCardView) : RecyclerView.ViewHolder(card)
