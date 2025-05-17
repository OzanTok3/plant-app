package com.ozantok.quizgenius.presentation.paywall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozantok.quizgenius.R
import com.ozantok.quizgenius.databinding.FragmentPaywallBinding
import com.ozantok.quizgenius.presentation.paywall.model.PaywallFeature
import com.ozantok.quizgenius.presentation.util.makeStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaywallFragment : Fragment() {

    private var _binding: FragmentPaywallBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaywallBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.post {
            makeStatusBarTransparent(view,isLightStatusBar = false)
        }

        binding.buttonClose.setOnClickListener {

            requireContext().getSharedPreferences("app_prefs", 0).edit()
                .putBoolean("onboarding_completed", true)
                .apply()

            findNavController().navigate(R.id.action_paywallFragment_to_homeFragment)
        }


        binding.buttonContinue.setOnClickListener {

        }

        setupFeatureList()
    }

    private fun setupFeatureList() {
        val features = listOf(
            PaywallFeature(R.drawable.icon_scanner, "Unlimited", "Plant Identify"),
            PaywallFeature(R.drawable.icon_speedometer, "Faster", "Process"),
            PaywallFeature(R.drawable.icon_scanner, "Unlimited", "Plant Identify"),
            PaywallFeature(R.drawable.icon_speedometer, "Faster", "Process")
        )

        val adapter = PaywallFeatureAdapter(features)
        binding.rvFeatures.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvFeatures.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}