package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book

interface BooksRepository {
    fun findBooksByAuthor(author: String): List<Book>
    fun findBooksByTitle(title: String): List<Book>
}
