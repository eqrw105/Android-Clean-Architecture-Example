package com.smin.core.domain.usecase

import com.smin.core.domain.model.User
import com.smin.core.domain.repository.UserRepository
import javax.inject.Inject

class GetUserUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
): GetUserUseCase {
    override suspend operator fun invoke(): User {
        return userRepository.getUser()
    }

}