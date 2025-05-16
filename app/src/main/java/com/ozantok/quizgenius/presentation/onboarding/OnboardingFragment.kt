package com.ozantok.quizgenius.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.ozantok.quizgenius.R
import com.ozantok.quizgenius.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var adapter: OnboardingAdapter
    private val pages = listOf(
        OnboardingPage("Welcome", "Welcome to QuizGenius!", R.drawable.onboarding1),
        OnboardingPage("Discover", "Discover awesome quizzes", R.drawable.onboarding2),
        OnboardingPage("Get Started", "Start playing now", R.drawable.onboarding3)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = OnboardingAdapter(pages)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == adapter.itemCount - 1) {
                    binding.btnNext.text = "Get Started"
                } else {
                    binding.btnNext.text = "Continue"
                }
            }
        })

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem + 1 < adapter.itemCount) {
                // Sonraki sayfaya geç
                binding.viewPager.currentItem = currentItem + 1
            } else {
                // Son sayfadaysak Paywall'a git
                findNavController().navigate(R.id.action_onboardingFragment_to_paywallFragment)
            }
        }
    }
}