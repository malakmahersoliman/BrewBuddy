package com.example.brewbuddy.presentation.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.ItemDrinkBinding
import com.example.brewbuddy.domain.model.Drink
import com.example.brewbuddy.presentation.common.format.Formatters

class FavoritesAdapter(
    private val onToggleFavorite: (Drink, Boolean) -> Unit
) : ListAdapter<Drink, FavoritesAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Drink>() {
        override fun areItemsTheSame(oldItem: Drink, newItem: Drink): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Drink, newItem: Drink): Boolean =
            oldItem == newItem
    }

    inner class VH(private val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Drink) = with(binding) {
            // Set drink name
            drinkName.text = item.name
            drinkPrice.text = item.price

            // Placeholder image until API fetch
            drinkImage.setImageDrawable(
                ContextCompat.getDrawable(root.context, R.drawable.ic_image_placeholder)
            )

            // Show as favorite (always true in favorites screen)
            favoriteIcon.setImageResource(R.drawable.ic_favorite_filled)

            // Toggle favorite callback
            favoriteIcon.setOnClickListener {
                onToggleFavorite(item, false) // false → remove from favorites
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ItemDrinkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
