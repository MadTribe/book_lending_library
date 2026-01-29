package com.patchwork.booklibrary.fixtures

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.services.BookLibrary

open class TestFixturesFactory() {
    companion object {
        fun create() : TestFixtures {
            val bookLibrary = object : BookLibrary {
                override fun findBooksByAuthor(string: String): List<Book> {
                    TODO("Not yet implemented")
                }

            }
            return TestFixtures(bookLibrary)
        }
    }

}

class TestFixtures(val bookLibrary: BookLibrary) {

}
