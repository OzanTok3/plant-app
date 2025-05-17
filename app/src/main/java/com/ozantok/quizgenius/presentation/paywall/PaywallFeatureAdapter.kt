package com.ozantok.quizgenius.presentation.paywall

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozantok.quizgenius.databinding.ItemPaywallFeatureBinding
import com.ozantok.quizgenius.presentation.paywall.model.PaywallFeature

class PaywallFeatureAdapter(private val items: List<PaywallFeature>) :
    RecyclerView.Adapter<PaywallFeatureAdapter.FeatureViewHolder>() {

    inner class FeatureViewHolder(val binding: ItemPaywallFeatureBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPaywallFeatureBinding.inflate(inflater, parent, false)
        return FeatureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivBanner.setImageResource(item.iconResId)
        holder.binding.tvTitle.text = item.title
        holder.binding.tvDescription.text = item.description
    }

    override fun getItemCount(): Int = items.size
}