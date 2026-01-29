package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.model.User
import com.patchwork.booklibrary.repositories.InMemoryBooksRepository
import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AdminServiceTest {
    lateinit var cut : AdminService
    @BeforeEach
    fun setUp() {

        val books = listOf(
            Book("0001", "Terry Pratchett","Weird sisters" , ISBN = "9780142428948", borrower = User("aaa","AAA") ),
            Book("0002", "Terry Pratchett","Guards! Guards!", ISBN = "978014200001"),
            Book("0004", "Douglas Adams","The Restaurant at the End of the Universe", ISBN = "978014202345", borrower = User("bbb","BBBB")),
            Book("0003", "Oxford University Press","Encyclopedia of Birds", ISBN = "234567896987", referenceBook = true),
        )
        val booksRepository = InMemoryBooksRepository(books)
        cut = AdminServiceImpl(booksRepository)
    }

    @Test
    fun `borrowed books are listed in admin report`() {
        val report = cut.reportBorrowedBooks()
        assertEquals(2,report.size)
        report.map { it.ISBN } shouldContain "9780142428948"
        report.map { it.ISBN } shouldContain "978014202345"
    }

}