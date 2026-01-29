package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.model.User

class InMemoryUserRepository(val users : List<User>) : UserRepository  {
    override fun findUserById(userId: String): User? {
       return users.findLast { user -> user.id == userId }
    }

}