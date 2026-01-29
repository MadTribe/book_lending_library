package com.patchwork.booklibrary.fixtures

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.repositories.InMemoryBooksRepository
import com.patchwork.booklibrary.services.BookLibrary
import com.patchwork.booklibrary.services.BookLibraryImpl

val books = listOf(
    Book("0001", "Terry Pratchett","Weird sisters"),
    Book("0002", "Terry Pratchett","Guards! Guards!"),
)

open class TestFixturesFactory() {
    companion object {
        fun create() : TestFixtures {
            val bookLibrary = BookLibraryImpl(InMemoryBooksRepository(books))

            return TestFixtures(bookLibrary)
        }
    }

    fun books(): List<Book> {
        return books
    }

}

class TestFixtures(val bookLibrary: BookLibrary) {

}
