package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book

class InMemoryBooksRepository(val books : List<Book>) : BooksRepository  {
    override fun findBooksByAuthor(author: String): List<Book> {
        return books.filter {
            it.author
                .indexOf(author, 0, true) >= 0
        }
    }
}