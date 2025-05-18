package com.ozantok.plantapp.presentation.home

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ozantok.core.util.UIState
import com.ozantok.plantapp.databinding.FragmentHomeBinding
import com.ozantok.plantapp.presentation.util.NetworkUtils
import com.ozantok.plantapp.presentation.util.addSoftShadow
import com.ozantok.plantapp.presentation.util.applyGoldGradient
import com.ozantok.plantapp.presentation.util.makeStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var questionsAdapter: QuestionsAdapter
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerViews()
        observeViewModel()
        view.post {
            makeStatusBarTransparent(view, isLightStatusBar = true)
        }
        applyGradientToTextViews()
        if (context?.let { NetworkUtils.isNetworkAvailable(it) } == true) {
            viewModel.fetchHomeData()
        } else {
            Toast.makeText(context, "İnternet bağlantısı yok", Toast.LENGTH_SHORT).show()
        }
        setupSearchBar()
    }

    private fun setupRecyclerViews() {
        questionsAdapter = QuestionsAdapter()
        binding.rvQuestions.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = questionsAdapter
        }

        categoriesAdapter = CategoriesAdapter()
        binding.rvCategories.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = categoriesAdapter
        }
    }

    private fun observeViewModel() {

        lifecycleScope.launch {
            viewModel.questions.collect { questions ->
                questionsAdapter.setItems(questions)
            }
        }

        lifecycleScope.launch {
            viewModel.categories.collect { categories ->
                categoriesAdapter.setItems(categories)
            }
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    UIState.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.homeRoot.isEnabled = false
                        binding.homeRoot.alpha = 0.5f
                    }

                    UIState.SUCCESS -> {
                        binding.progressBar.visibility = View.GONE
                        binding.homeRoot.isEnabled = true
                        binding.homeRoot.alpha = 1f
                    }

                    UIState.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.homeRoot.isEnabled = true
                        binding.homeRoot.alpha = 1f
                        showErrorDialog()
                    }
                }
            }
        }
    }


    private fun showErrorDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Oops!")
            .setMessage("Veriler alınırken bir hata oluştu. Tekrar denemek ister misiniz?")
            .setCancelable(false)
            .setPositiveButton("Tekrar Dene") { _, _ ->
                viewModel.fetchHomeData()
            }
            .setNegativeButton("İptal", null)
            .show()
    }

    private fun applyGradientToTextViews() {
        binding.tvPremiumTitle.alpha = 1f
        binding.tvPremiumSubtitle.alpha = 1f
        binding.tvPremiumTitle.applyGoldGradient()
        binding.tvPremiumTitle.addSoftShadow()
        binding.tvPremiumSubtitle.applyGoldGradient()
        binding.tvPremiumSubtitle.addSoftShadow()
    }

    private fun setupSearchBar() {
        lifecycleScope.launch {
            callbackFlow {
                val watcher = object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {
                        trySend(s.toString())
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }
                }
                binding.etSearch.addTextChangedListener(watcher)
                awaitClose { binding.etSearch.removeTextChangedListener(watcher) }
            }
                .debounce(3000L)
                .filter { it.isNotBlank() }
                .collect { query ->
                    searchViewModel.saveQuery(query)
                    Log.d("DebouncedQuery", query)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}