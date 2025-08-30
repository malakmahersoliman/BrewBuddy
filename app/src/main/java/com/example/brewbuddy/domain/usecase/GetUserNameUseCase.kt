package com.example.brewbuddy.domain.usecase

import com.example.brewbuddy.domain.repo.UserRepo
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val repo: UserRepo
) {
    operator fun invoke(): String? = repo.getName()
}
