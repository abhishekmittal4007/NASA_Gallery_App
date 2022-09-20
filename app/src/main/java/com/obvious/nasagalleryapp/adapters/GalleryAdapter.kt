package com.obvious.nasagalleryapp.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.nasagalleryapp.R
import com.obvious.nasagalleryapp.databinding.GalleryItemBinding

class GalleryAdapter(
    private var items: List<ModalGalleryItem>,
    private var mContext: Context,
    private val onClick: (Int) -> Unit,
    private val onFavClick: (Int) -> Unit
) : RecyclerView.Adapter<GalleryAdapter.ItemViewHolder>() {

  inner class ItemViewHolder(val galleryItemBinding: GalleryItemBinding) :
      RecyclerView.ViewHolder(galleryItemBinding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    return ItemViewHolder(
        GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = items[position]
    holder.galleryItemBinding.apply {
      holder.itemView.apply {
        apodTitle.text = item.title
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.apply {
          setColorSchemeColors(Color.rgb(105, 240, 174))
          strokeWidth = 10f
          centerRadius = 40f
          start()
        }
        favButton.setOnClickListener { onFavClick(position) }

        if (item.isFavorite) favButton.setImageResource(R.drawable.ic_star_filled)
        else favButton.setImageResource(R.drawable.ic_star_outline)

        Glide.with(holder.itemView.context)
            .load(item.url)
            .placeholder(circularProgressDrawable)
            .into(apodImage)

        containerCard.setOnClickListener { onClick(position) }
      }
    }
  }

  override fun getItemCount() = items.size
  /*
  fun updateItems(newItems: List<ModalGalleryItem>) {
    items.clear()
    items.addAll(newItems)
    notifyDataSetChanged()
  }*/
}
