package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.model.User

interface UserRepository {
    fun findUserById(userId: String): User?
}
