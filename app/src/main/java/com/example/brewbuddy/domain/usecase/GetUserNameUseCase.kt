package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.repository.UserRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val repo: UserRepository
) {
    operator fun invoke(): String? = repo.getName()
}