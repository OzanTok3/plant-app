package com.ozantok.quizgenius.presentation.paywall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ozantok.quizgenius.R
import com.ozantok.quizgenius.databinding.FragmentPaywallBinding

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


        binding.btnClose.setOnClickListener {

            requireContext().getSharedPreferences("app_prefs", 0).edit()
                .putBoolean("onboarding_completed", true)
                .apply()

            findNavController().navigate(R.id.action_paywallFragment_to_homeFragment)
        }


        binding.btnSubscribe.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}