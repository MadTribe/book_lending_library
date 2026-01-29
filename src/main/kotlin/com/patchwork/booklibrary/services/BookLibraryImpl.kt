package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository
import com.patchwork.booklibrary.repositories.UserRepository

class BookLibraryImpl(val booksRepository: BooksRepository, val userRepository: UserRepository) : BookLibrary {
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
        val book = booksRepository.findBooksByLibraryItemId(libraryItemId)
           ?: return BorrowResult.Failure(BorrowError.BookNotFound)

        if (book.borrower != null){
            return BorrowResult.Failure(BorrowError.AlreadyBorrowed)
        }

        val borrower = userRepository.findUserById(userId)
            ?: return BorrowResult.Failure(BorrowError.UnknownUser)

        val newBook = book.copy(borrower = borrower )

        booksRepository.updateBook(newBook)

        return BorrowResult.Success
    }

    override fun userLoans(userId: String): List<Book> {
        return booksRepository.finBooksLoanedTo(userId)
    }
}