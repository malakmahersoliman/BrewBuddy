package com.example.brewbuddy.presentation.orders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.R
import com.example.brewbuddy.domain.model.Order
import com.google.android.material.imageview.ShapeableImageView
import java.text.SimpleDateFormat
import java.util.Locale

class OrderAdapter(
    private val order:  MutableList<Order> = mutableListOf()
): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    fun updateOrders(newOrders: List<Order>) {
        order.clear()
        order.addAll(newOrders)
        notifyDataSetChanged()
    }


    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val drinkImage: ShapeableImageView = itemView.findViewById(R.id.drinkImage)
        private val quantity: TextView = itemView.findViewById(R.id.quantity)
        private val drinkName: TextView = itemView.findViewById(R.id.drinkName)
        private val orderDate: TextView = itemView.findViewById(R.id.orderDate)
        private val details: TextView = itemView.findViewById(R.id.details)

        fun bind(order: Order) {

            // Glide.with(itemView).load(order.imageUrl).into(drinkImage)
            Glide.with(itemView)
                .load(order.imageUrl)
                .placeholder(R.drawable.placeholder_drink)
                .into(drinkImage)




            quantity.text = "${order.quantity}x"
            drinkName.text = order.name

            // Formatting  "yyyy-MM-dd" → "dd/MM"
            orderDate.text = formatDate(order.date)

            details.setOnClickListener {
                // onDetailsClick(order)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(order[position])
    }

    override fun getItemCount(): Int = order.size

    private fun formatDate(dateString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd/MM", Locale.getDefault())

        return try {
            val date = inputFormat.parse(dateString)
            date?.let { outputFormat.format(it) } ?: dateString
        } catch (e: Exception) {
            dateString // Return original string if parsing fails
        }
    }
}