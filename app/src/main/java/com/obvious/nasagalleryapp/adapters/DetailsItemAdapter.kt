package com.obvious.nasagalleryapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.obvious.domain.models.ModalGalleryItem
import com.obvious.nasagalleryapp.R
import com.obvious.nasagalleryapp.databinding.DetailItemBinding

class DetailsItemAdapter(
    private val items: List<ModalGalleryItem>,
) : RecyclerView.Adapter<DetailsItemAdapter.ItemViewHolder>() {

  inner class ItemViewHolder(val detailItemBinding: DetailItemBinding) :
      RecyclerView.ViewHolder(detailItemBinding.root)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
    val binding = DetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ItemViewHolder(binding)
  }

  override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
    val item = items[position]
    holder.detailItemBinding.apply {
      holder.itemView.apply {
        title.text = item.title
        item.copyright?.let {
          copyright.text = "© $it"
          copyright.visibility = View.VISIBLE
        }
        date.text = item.date
        explanation.text = item.explanation
        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.apply {
          setColorSchemeColors(ContextCompat.getColor(holder.itemView.context, R.color.neon_green))
          strokeWidth = 10f
          centerRadius = 40f
          start()
        }

        Glide.with(holder.itemView.context)
            .load(item.url)
            .placeholder(circularProgressDrawable)
            .into(image)
      }
    }
  }

  override fun getItemCount() = items.size
}
