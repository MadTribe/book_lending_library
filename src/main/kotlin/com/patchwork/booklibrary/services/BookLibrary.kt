package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book

interface BookLibrary {
    fun findBooksByAuthor(author: String) : List<Book>
    fun findBooksByTitle(title: String) : List<Book>
    fun findBooksByISBN(isbn: String) : List<Book>
    fun borrow(userId: String, libraryItemId: String) : BorrowResult
    fun userLoans(userId: String) : List<Book>

}
sealed interface BorrowError {
    data object BookNotFound : BorrowError
    data object AlreadyBorrowed : BorrowError
    data object ReferenceBook : BorrowError
}

sealed interface BorrowResult {
    data object Success : BorrowResult
    data class Failure(val error: BorrowError) : BorrowResult
}

