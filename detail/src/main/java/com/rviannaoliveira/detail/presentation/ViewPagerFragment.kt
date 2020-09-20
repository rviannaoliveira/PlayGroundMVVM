package com.rviannaoliveira.detail.presentation

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rviannaoliveira.detail.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class ImageAdapter(activity : AppCompatActivity, private val images : List<String>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = images.size

    override fun createFragment(position: Int): Fragment {
        val image = images[position]
        val fragment = ImageFragment()
        fragment.arguments = Bundle().apply {
            putString(URL, image)
        }
        return fragment
    }
}

private const val URL = "URL"

class ImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.item_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(URL) }?.apply {
            val image = view.findViewById<ImageView>(R.id.image)
            Picasso.get()
                .load(getString(URL))
                .placeholder(requireContext().getDrawable(R.drawable.empty)!!)
                .into(object : Target {
                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        image.setImageDrawable(placeHolderDrawable)
                    }

                    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                        image.setImageDrawable(requireContext().getDrawable(R.drawable.empty)!!)
                    }

                    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                        image.setImageBitmap(bitmap)
                    }
                })

        }
    }
}