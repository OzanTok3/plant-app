package com.ozantok.quizgenius.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozantok.quizgenius.databinding.ItemCategoryBinding
import com.ozantok.quizgenius.domain.model.Category

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    private val categories = mutableListOf<Category>()

    fun setItems(newItems: List<Category>) {
        categories.clear()
        categories.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class CategoryViewHolder(val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.tvCategoryName.text = category.title

        Glide.with(holder.itemView.context)
            .load(category.imageUrl)
            .into(holder.binding.ivCategoryImage)
    }

    override fun getItemCount(): Int = categories.size
}