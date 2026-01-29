package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book

interface AdminService {
    fun reportBorrowedBooks(): List<Book>
}
