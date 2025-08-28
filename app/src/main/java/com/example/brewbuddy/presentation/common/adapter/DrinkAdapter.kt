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

// Wrapper to include "isFavorite" flag with Drink
data class UiDrink(
    val drink: Drink,
    val isFavorite: Boolean
)

class DrinkAdapter(
    private val onToggleFavorite: (Drink, Boolean) -> Unit
) : ListAdapter<UiDrink, DrinkAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<UiDrink>() {
        override fun areItemsTheSame(oldItem: UiDrink, newItem: UiDrink): Boolean =
            oldItem.drink.id == newItem.drink.id

        override fun areContentsTheSame(oldItem: UiDrink, newItem: UiDrink): Boolean =
            oldItem == newItem
    }

    inner class VH(private val binding: ItemDrinkBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UiDrink) = with(binding) {
            //  Set drink name
            drinkName.text = item.drink.name

            // Format and set price
            drinkPrice.text = Formatters.formatPrice(item.drink.price)

            // Placeholder image (replace later with Glide/Picasso when API is ready)
            drinkImage.setImageDrawable(
                ContextCompat.getDrawable(root.context, R.drawable.placeholder_drink)
            )

            //  Handle favorite state
            favoriteIcon.setImageResource(
                if (item.isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
            )
            favoriteIcon.contentDescription = root.context.getString(
                if (item.isFavorite) R.string.cd_unfavorite else R.string.cd_favorite
            )

            //  Toggle favorite on click
            favoriteIcon.setOnClickListener {
                onToggleFavorite(item.drink, !item.isFavorite)
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
