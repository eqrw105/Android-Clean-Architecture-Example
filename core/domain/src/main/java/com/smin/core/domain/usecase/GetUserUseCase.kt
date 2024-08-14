package com.smin.core.domain.usecase

import com.smin.core.domain.model.User

interface GetUserUseCase {
    suspend operator fun invoke(): User
}