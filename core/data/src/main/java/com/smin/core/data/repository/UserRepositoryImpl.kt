package com.smin.core.data.repository

import com.smin.core.data.entitiy.UserResponse
import com.smin.core.data.mapper.toUser
import com.smin.core.domain.model.User
import com.smin.core.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    override suspend fun getUser(): User {
        //mock..
        //TODO : Add Data Source
        return UserResponse(1).toUser()
    }
}