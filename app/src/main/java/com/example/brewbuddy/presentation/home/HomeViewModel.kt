package com.example.brewbuddy.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brewbuddy.domain.model.Coffee
import com.example.brewbuddy.domain.usecase.GetRecommendationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase
) : ViewModel() {

    private val _recommendations = MutableStateFlow<List<Coffee>>(emptyList())
    val recommendations: StateFlow<List<Coffee>> = _recommendations.asStateFlow()

    private val _bestSeller = MutableStateFlow<Coffee?>(null)
    val bestSeller: StateFlow<Coffee?> = _bestSeller.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        loadRecommendationsAndBestSeller()
    }

    private fun loadRecommendationsAndBestSeller() {
        _isLoading.value = true
        _error.value = null

        viewModelScope.launch {
            try {
                // Get 7 recommendations: 1 for best seller + 6 for the list
                val allRecommendations = getRecommendationsUseCase(7)

                if (allRecommendations.isNotEmpty()) {
                    // First item is best seller, rest are recommendations
                    _bestSeller.value = allRecommendations.first()
                    _recommendations.value = allRecommendations.drop(1).take(6)
                } else {
                    _error.value = "No recommendations available"
                }
            } catch (e: Exception) {
                _error.value = "Failed to load data: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun refreshData() {
        loadHomeData()
    }

    fun clearError() {
        _error.value = null
    }
}