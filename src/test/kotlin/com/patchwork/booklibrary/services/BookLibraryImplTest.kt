package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.fixtures.TestFixtures
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.InMemoryBooksRepository
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
    }
}