package com.smin.core.data.mapper

import com.smin.core.data.entitiy.UserResponse
import com.smin.core.domain.model.User

fun UserResponse.toUser(): User {
    return User(id = userId)
}