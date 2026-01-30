package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.BooksRepository
import org.slf4j.LoggerFactory.getLogger

class AdminServiceImpl(val booksRepository: BooksRepository) : AdminService {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        @JvmStatic
        private val logger = getLogger(javaClass.enclosingClass)
    }
    override fun reportBorrowedBooks(): List<Book> {
        logger.debug("reportBorrowedBooks Entry")
        val report = booksRepository.findBorrowedBooks();
        logger.debug("reportBorrowedBooks exit")
        return report
    }
}
