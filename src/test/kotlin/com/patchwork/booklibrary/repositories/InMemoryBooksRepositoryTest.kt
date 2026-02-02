package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.Clock

class InMemoryBooksRepositoryTest {
    lateinit var cut : InMemoryBooksRepository
    @BeforeEach
    fun setUp() {
        cut = InMemoryBooksRepository(testData())
    }


    @Test
    fun time_findBooksByAuthor() {
        val clock: Clock = Clock.System
        val start1 = clock.now()
        for (i in 1..1000) {
            val searchResult = cut.findBooksByTitle("WEIRd Sis")
        }
        val end1 = clock.now()
        val duration1 = end1.minus(start1).inWholeMilliseconds
        println("Higher order version " + duration1)
        val start2 = clock.now()
        for (i in 1..1000) {
            val searchResult = cut.findBooksByTitleV1("WEIRd Sis")
        }
        val end2 = clock.now()
        val duration2 = end2.minus(start2).inWholeMilliseconds
        println("Old Version " + duration2)


        println("factor difference " + duration1/duration2)


    }

    @Test
    fun findBooksByTitle() {
    }

    @Test
    fun findBooksByISBN() {
    }

    @Test
    fun findBooksByTitleV1() {
    }

    fun testData() : List<Book> {
        val ret = mutableListOf<Book>()

        for ( i in 1..1000) {
           ret.add(Book(""+i,
               "author " + 1,
               "title " + i,
               "description " + i,
               false,
               null))
        }

        return  ret
    }
}