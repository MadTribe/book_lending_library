package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository
import com.patchwork.booklibrary.repositories.UserRepository
import org.slf4j.LoggerFactory.getLogger

class BookLibraryImpl(val booksRepository: BooksRepository, val userRepository: UserRepository) : BookLibrary {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = getLogger(javaClass.enclosingClass)
    }

    override fun findBooksByAuthor(author: String): List<Book> {
        logger.debug("findBooksByAuthor {}", author)
        return booksRepository.findBooksByAuthor(author)
    }

    override fun findBooksByTitle(title: String): List<Book> {
        logger.debug("findBooksByTitle {}", title)
        return booksRepository.findBooksByTitle(title)
    }

    override fun findBooksByISBN(isbn: String): List<Book> {
        logger.debug("findBooksByISBN {}", isbn)
        return booksRepository.findBooksByISBN(isbn)
    }

    @Synchronized // we should not let more than one thread in the borrow func at a time.
    override fun borrow(
        userId: String,
        libraryItemId: String
    ): BorrowResult {
        logger.info("User {} is trying to borrow item {}", userId, libraryItemId)
        val book = booksRepository.findBooksByLibraryItemId(libraryItemId)
           ?: return BorrowResult.Failure(BorrowError.BookNotFound)

        if (book.referenceBook){
            logger.debug("Borrow failure. Is reference book user {}, item {} ", userId ,libraryItemId)
            return BorrowResult.Failure(BorrowError.ReferenceBook)
        }

        if (book.borrower != null){
            logger.debug("Borrow failure by {}. Is already borrowed by user {}, item {} ", userId, book.borrower ,libraryItemId)
            return BorrowResult.Failure(BorrowError.AlreadyBorrowed)
        }

        val borrower = userRepository.findUserById(userId)

        if (borrower == null){
            logger.warn("borrower {} is unknown user ", userId )
            return BorrowResult.Failure(BorrowError.UnknownUser)
        }

        val newBook = book.copy(borrower = borrower )

        booksRepository.updateBook(newBook)

        logger.debug("borrow success user {} item {} ", userId ,libraryItemId)
        return BorrowResult.Success
    }

    override fun userLoans(userId: String): List<Book> {
        logger.debug("userLoans {}", userId)
        return booksRepository.finBooksLoanedTo(userId)
    }
}