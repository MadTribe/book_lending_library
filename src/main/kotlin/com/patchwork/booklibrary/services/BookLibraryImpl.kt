package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository

class BookLibraryImpl(val booksRepository: BooksRepository) : BookLibrary {
    override fun findBooksByAuthor(author: String): List<Book> {
       return booksRepository.findBooksByAuthor(author)
    }
}