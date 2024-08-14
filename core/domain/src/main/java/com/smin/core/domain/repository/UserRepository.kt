package com.smin.core.domain.repository

import com.smin.core.domain.model.User

interface UserRepository {
    suspend fun getUser(): User
}