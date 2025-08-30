package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(
    private val repo: UserRepository
) {
    operator fun invoke(name: String) = repo.saveName(name)
}