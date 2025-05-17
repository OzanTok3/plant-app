package com.ozantok.quizgenius.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ozantok.quizgenius.databinding.ItemQuestionBinding
import com.ozantok.quizgenius.domain.model.Question

class QuestionsAdapter : RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    private val questions = mutableListOf<Question>()

    fun setItems(newItems: List<Question>) {
        questions.clear()
        questions.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class QuestionViewHolder(val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.binding.tvTitle.text = questions[position].title
        Glide.with(holder.itemView.context)
            .load(questions[position].imageUri)
            .into(holder.binding.ivBanner)
    }

    override fun getItemCount(): Int = questions.size
}