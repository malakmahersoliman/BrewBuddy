// presentation/home/HomeFragment.kt
package com.example.brewbuddy.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.brewbuddy.R
import com.example.brewbuddy.databinding.FragmentHomeBinding
import com.example.brewbuddy.domain.model.Coffee
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var recommendationAdapter: RecommendationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        recommendationAdapter = RecommendationAdapter()
        recommendationAdapter.setOnClick { recommendation ->
            navigateToRecommendationDetails(recommendation)
        }

        binding.rvRecommendations.apply {
            adapter = recommendationAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun setupObservers() {
        // Observe recommendations
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.recommendations.collect { recommendations ->
                    recommendationAdapter.setRecommendationList(recommendations)
                }
            }
        }

        // Observe best seller
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.bestSeller.collect { bestSeller ->
                    bestSeller?.let { updateBestSellerUI(it) }
                }
            }
        }

        // Observe loading state
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isLoading.collect { isLoading ->
                    if (isLoading) {
                        showLoadingState()
                    } else {
                        hideLoadingState()
                    }
                }
            }
        }

        // Observe errors
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.error.collect { error ->
                    error?.let {
                        showError(it)
                        viewModel.clearError()
                    }
                }
            }
        }


    }

    private fun updateBestSellerUI(bestSeller: Coffee) {
        binding.apply {
            tvBestSellerName.text = bestSeller.title

            // Load best seller image
            Glide.with(requireContext())
                .load(bestSeller.image)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(binding.ibBestSellerImage)
        }
    }

    private fun showLoadingState() {
        binding.rvRecommendations.visibility = View.GONE

    }

    private fun hideLoadingState() {
        binding.rvRecommendations.visibility = View.VISIBLE
    }

    private fun setupClickListeners() {
        binding.ibMoreInfo.setOnClickListener {
            viewModel.bestSeller.value?.let { bestSeller ->
                navigateToBestSellerDetails(bestSeller)
            }
        }

        binding.ibBestSellerImage.setOnClickListener {
            viewModel.bestSeller.value?.let { bestSeller ->
                navigateToBestSellerDetails(bestSeller)
            }
        }

        binding.cardBestSeller.setOnClickListener {
            viewModel.bestSeller.value?.let { bestSeller ->
                navigateToBestSellerDetails(bestSeller)
            }
        }

    }

    private fun navigateToBestSellerDetails(bestSeller: Coffee) {
        // Navigate to best seller details
         findNavController().navigate(
             HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(
                 bestSeller.id,
                 bestSeller.title,
                 bestSeller.price,
                 bestSeller.description,
                 bestSeller.image,
             )
         )
    }

    private fun navigateToRecommendationDetails(recommendation: Coffee) {
        // Navigate to recommendation details
        // findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(recommendation.id))
    }

    private fun showError(message: String) {
        // Show error message (Snackbar, Toast, etc.)
        // Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}