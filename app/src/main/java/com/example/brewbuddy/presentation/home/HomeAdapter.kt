package com.example.brewbuddy.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.databinding.ItemRecommendationBinding
import com.example.brewbuddy.domain.model.Coffee

class RecommendationAdapter : RecyclerView.Adapter<RecommendationAdapter.Holder> () {

    private var recommendationsList : List<Coffee?>? = null

    private lateinit var onCLick : (Coffee) -> Unit

    fun setOnClick(onClick : (Coffee) -> Unit) {
        this.onCLick = onClick
    }

    inner class Holder(val binding: ItemRecommendationBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recommendation : Coffee) {
            binding.apply {
                tvRecommendationName.text = recommendation.title

                tvRecommendationPrice.text = recommendation.price

                Glide.with(binding.root)
                    .load(recommendation.image)
                    .placeholder(android.R.drawable.ic_menu_report_image)
                    .centerCrop()
                    .into(ivRecommendationImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int = recommendationsList?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val recommendation = recommendationsList!![position]!!

        holder.bind(recommendation)

        holder.binding.root.setOnClickListener {
            onCLick.invoke(recommendation)
        }
    }
    fun setRecommendationList(recommendationsList: List<Coffee>?) {
        this.recommendationsList = recommendationsList
        notifyDataSetChanged()
    }
}