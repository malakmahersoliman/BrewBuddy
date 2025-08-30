package com.example.brewbuddy.presentation.onboarding


import androidx.lifecycle.ViewModel
import com.example.brewbuddy.domain.usecase.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveUserName: SaveUserNameUseCase
) : ViewModel() {
    val name = MutableStateFlow("")

    fun onContinue() {
        val n = name.value.trim()
        if (n.isNotBlank()) saveUserName(n)
    }
}