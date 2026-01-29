package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository

class AdminServiceImpl(val booksRepository: BooksRepository) : AdminService {
    override fun reportBorrowedBooks(): List<Book> {
        return emptyList()
    }
}
