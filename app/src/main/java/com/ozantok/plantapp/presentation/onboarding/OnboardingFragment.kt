package com.ozantok.plantapp.presentation.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ozantok.plantapp.R
import com.ozantok.plantapp.databinding.FragmentOnboardingBinding
import com.ozantok.plantapp.presentation.util.makeStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding
    private lateinit var adapter: OnboardingAdapter

    private lateinit var pages: List<OnboardingPage>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        pages = listOf(
            OnboardingPage(getString(R.string.onboarding_title_1), "Identify more than 3000+ plants and 88% accuracy.", R.drawable.onboarding1),
            OnboardingPage(getString(R.string.onboarding_title_2), "", R.drawable.onboarding2),
            OnboardingPage(getString(R.string.onboarding_title_3), "", R.drawable.onboarding3)
        )

        adapter = OnboardingAdapter(pages)
        binding.viewPager.adapter = adapter

        view.post{
            makeStatusBarTransparent(view, isLightStatusBar = true)
        }

        binding.btnNext.text = "Get Started"

        setupCustomTabLayout()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.btnNext.text = if (position == 0) "Get Started" else "Continue"

                updateTabIndicators(position)
            }
        })

        binding.btnNext.setOnClickListener {
            val currentItem = binding.viewPager.currentItem
            if (currentItem + 1 < adapter.itemCount) {

                binding.viewPager.currentItem = currentItem + 1
            } else {

                findNavController().navigate(R.id.action_onboardingFragment_to_paywallFragment)
            }
        }
    }

    private fun setupCustomTabLayout() {

        binding.tabLayout.removeAllTabs()
        for (i in pages.indices) {
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val customTabView = LayoutInflater.from(requireContext())
                .inflate(R.layout.layout_custom_tab_indicator, null)
            tab.customView = customTabView

            updateTabIndicator(tab, position == 0)
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabIndicator(tab, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabIndicator(tab, false)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun updateTabIndicators(selectedPosition: Int) {
        for (i in 0 until binding.tabLayout.tabCount) {
            val tab = binding.tabLayout.getTabAt(i)
            tab?.let {
                updateTabIndicator(it, i == selectedPosition)
            }
        }
    }

    private fun updateTabIndicator(tab: TabLayout.Tab, isSelected: Boolean) {
        val customView = tab.customView ?: return
        val indicator = customView.findViewById<ImageView>(R.id.tab_indicator)

        if (isSelected) {
            indicator.setImageResource(R.drawable.tab_indicator_selected)
        } else {
            indicator.setImageResource(R.drawable.tab_indicator_unselected)
        }
    }
}