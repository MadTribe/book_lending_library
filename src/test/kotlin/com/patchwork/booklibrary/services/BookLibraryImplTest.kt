package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.fixtures.TestFixtures
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.InMemoryBooksRepository
import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BookLibraryImplTest {
    lateinit var cut : BookLibraryImpl
    @BeforeEach
    fun setUp() {
       val booksRepository = InMemoryBooksRepository(TestFixturesFactory().books())
       cut = BookLibraryImpl(booksRepository)
    }

    @Test
    fun `author doesnt exist findBooksByAuthor() returns empty list`() {
        val searchResult = cut.findBooksByAuthor("Zaphod Beeblebrox")
        assertTrue(searchResult.isEmpty())
    }
    @Test
    fun `given 2 books by author does exist findBooksByAuthor() returns both books`() {
        val searchResult = cut.findBooksByAuthor("Terry Pratchett")
        assertEquals(2, searchResult.size )
    }
    @Test
    fun `given partial and case insensitive findBooksByAuthor() returns both books`() {
        val searchResult = cut.findBooksByAuthor("PratCHET")

        assertEquals(2, searchResult.size )
        searchResult.map{ it.title } shouldContain "Guards! Guards!"
        searchResult.map{ it.title } shouldContain "Weird sisters"
    }

    @Test
    fun `title doesn't exist findBooksByTitle returns empty List`(){
       val searchResult = cut.findBooksByTitle("Charlie the Choo Choo")
        assertTrue(searchResult.isEmpty())
    }

    @Test
    fun `title exists findBooksByTitle returns matching book`(){
        val searchResult = cut.findBooksByTitle("WEIRd Sis")
        assertEquals(1, searchResult.size)
    }

    @Test
    fun `ISBN not known findBooksByIBN returns empty list`(){
        val searchResult = cut.findBooksByISBN("234567890")
        assertTrue(searchResult.isEmpty())
    }


    @Test
    fun `ISBN known findBooksByISBN returns one book`(){
        val searchResult = cut.findBooksByISBN("978014200001")
        assertEquals(1, searchResult.size )
        searchResult.map{ it.title } shouldContain "Guards! Guards!"
    }

    @Test
    fun `book doesnt exist borrow returns failure`(){
        val borrowResult = cut.borrow("0002", "DOES_NOT_EXIST" )
        when (borrowResult ){
            is BorrowResult.Success -> {
               fail("expected faile")
            } else -> {
               assertEquals(BorrowResult.Failure(BorrowError.BookNotFound), borrowResult)
            }

        }
    }
    @Test
    fun `normal available book exists, borrow returns success`(){
        val borrowResult = cut.borrow("0002", "0001" )
        when (borrowResult ){
            is BorrowResult.Success -> {
            } else -> {
                fail("Unexpected fail")
            }
        }
    }
}