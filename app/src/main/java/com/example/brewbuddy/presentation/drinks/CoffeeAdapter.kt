package com.example.brewbuddy.presentation.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.data.entities.Coffee
import com.example.brewbuddy.databinding.CoffeeItemBinding

class CoffeeAdapter(
    private var items: List<Coffee>,
    private val onAddClick: (Coffee) -> Unit
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val binding = CoffeeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoffeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {

        val item = items[position]
        holder.binding.tvName.text = item.title
        holder.  binding.tvPrice.text = item.price
        holder. binding.tvIngredients.text = item.ingredients.joinToString(", ")
        Glide.with( holder.binding.ivCoffee.context).load(item.image).into( holder.binding.ivCoffee)

        holder. binding.btnAdd.setOnClickListener {
            onAddClick(item)
        }


    }

    override fun getItemCount() = items.size

    fun updateList(newList: List<Coffee>) {
        items = newList
        notifyDataSetChanged()
    }

    fun currentList(): List<Coffee> = items
    inner class CoffeeViewHolder( val binding: CoffeeItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}