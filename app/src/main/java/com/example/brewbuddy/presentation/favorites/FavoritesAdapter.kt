package com.example.brewbuddy.presentation.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.R
import com.example.brewbuddy.domain.model.Favorite

class FavoritesAdapter(
    private val onRemoveClicked: (Favorite) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>() {

    private val items = mutableListOf<Favorite>()

    fun submitList(newList: List<Favorite>) {
        items.clear()
        items.addAll(newList)
        notifyDataSetChanged()
    }

    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.drinkImage)
        val name: TextView = itemView.findViewById(R.id.drinkName)
        val price: TextView = itemView.findViewById(R.id.drinkPrice)
        val removeBtn: ImageButton = itemView.findViewById(R.id.removeFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_drink, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = items[position]

        // Set name & price
        holder.name.text = item.name
        holder.price.text = "Rp ${item.price}"

        // Load image with Glide (or placeholder)
        Glide.with(holder.itemView)
            .load(item.image)
            .placeholder(R.drawable.placeholder_drink)
            .into(holder.image)

        // Remove from favorites action
        holder.removeBtn.setOnClickListener {
            onRemoveClicked(item)
        }
    }

    override fun getItemCount(): Int = items.size
}
