package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository

class BookLibraryImpl(val booksRepository: BooksRepository) : BookLibrary {
    override fun findBooksByAuthor(author: String): List<Book> {
       return booksRepository.findBooksByAuthor(author)
    }

    override fun findBooksByTitle(title: String): List<Book> {
        return booksRepository.findBooksByTitle(title)
    }

    override fun findBooksByISBN(isbn: String): List<Book> {
        return booksRepository.findBooksByISBN(isbn)
    }

    override fun borrow(
        userId: String,
        libraryItemId: String
    ): BorrowResult {
        val books= booksRepository.findBooksByLibraryItemId(libraryItemId)
        if (books.isEmpty()) {
            return BorrowResult.Failure(BorrowError.BookNotFound)
        }
        return BorrowResult.Success
    }

    override fun userLoans(userId: String): List<Book> {
        TODO("Not yet implemented")
    }
}