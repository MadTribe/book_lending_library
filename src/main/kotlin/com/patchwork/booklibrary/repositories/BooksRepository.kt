package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book

interface BooksRepository {
    fun findBooksByAuthor(author: String): List<Book>
    fun findBooksByTitle(title: String): List<Book>
    fun findBooksByISBN(isbn: String): List<Book>
    fun findBooksByLibraryItemId(id: String): Book?
    fun updateBook(new: Book)
    fun finBooksLoanedTo(userId: String): kotlin.collections.List<com.patchwork.booklibrary.model.Book>
    fun findBorrowedBooks() : List<Book>
}
