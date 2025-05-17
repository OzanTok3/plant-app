package com.ozantok.plantapp.presentation.onboarding

import android.text.SpannableString
import android.text.Spanned
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.ozantok.plantapp.R

class OnboardingAdapter(private val pages: List<OnboardingPage>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    inner class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText: TextView = itemView.findViewById(R.id.tvTitle)
        private val descText: TextView = itemView.findViewById(R.id.tvDescription)
        private val imageView: ImageView = itemView.findViewById(R.id.ivImage)
        private val imageViewSecondAndThirdPages: ImageView = itemView.findViewById(R.id.ivImageSecondAndThirdPages)

        fun bind(page: OnboardingPage) {
            val context = itemView.context
            descText.text = page.description
            imageView.setImageResource(page.imageRes)

            val secondImageLayoutParams = imageViewSecondAndThirdPages.layoutParams as ViewGroup.MarginLayoutParams

            when (adapterPosition) {
                0 -> {
                    val fullText = "Welcome to PlantApp"
                    val spannable = SpannableString(fullText)

                    val typeface = ResourcesCompat.getFont(context, R.font.roboto_semibold)
                    if (typeface != null) {
                        spannable.setSpan(
                            CustomTypefaceSpan(typeface),
                            fullText.indexOf("PlantApp"),
                            fullText.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }

                    titleText.text = spannable
                    titleText.typeface = ResourcesCompat.getFont(context, R.font.roboto)
                    imageViewSecondAndThirdPages.visibility = View.GONE
                }

                1 -> {
                    val fullText ="Take a photo to identify\nthe plant!"
                    val spannable = SpannableString(fullText)

                    val typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
                    if (typeface != null) {
                        spannable.setSpan(
                            CustomTypefaceSpan(typeface),
                            fullText.indexOf("identify"),
                            fullText.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }

                    titleText.text = spannable
                    titleText.typeface = ResourcesCompat.getFont(context, R.font.roboto_semibold)
                    imageViewSecondAndThirdPages.visibility = View.VISIBLE
                }

                2 -> {
                    val fullText = "Get plant care guides"
                    val spannable = SpannableString(fullText)

                    val typeface = ResourcesCompat.getFont(context, R.font.roboto_bold)
                    if (typeface != null) {
                        spannable.setSpan(
                            CustomTypefaceSpan(typeface),
                            fullText.indexOf("care guides"),
                            fullText.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        )
                    }

                    titleText.text = spannable
                    titleText.typeface = ResourcesCompat.getFont(context, R.font.roboto_semibold)
                    imageViewSecondAndThirdPages.visibility = View.VISIBLE


                    secondImageLayoutParams.width = dpToPx(context, 350)
                    secondImageLayoutParams.height = dpToPx(context, 50)
                    secondImageLayoutParams.setMargins(
                        dpToPx(context, 0),
                        dpToPx(context, 74),
                        dpToPx(context, 20),
                        dpToPx(context, 24)
                    )
                    imageViewSecondAndThirdPages.layoutParams = secondImageLayoutParams
                }

                else -> {
                    titleText.text = page.title
                    titleText.typeface = ResourcesCompat.getFont(context, R.font.roboto_semibold)
                    imageViewSecondAndThirdPages.visibility = View.GONE
                }
            }
        }
    }

    private fun dpToPx(context: android.content.Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_onboarding_page, parent, false)
        return OnboardingViewHolder(view)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.bind(pages[position])
    }

    override fun getItemCount(): Int = pages.size
}