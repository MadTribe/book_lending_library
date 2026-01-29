package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book

class InMemoryBooksRepository(var books : List<Book>) : BooksRepository  {
    override fun findBooksByAuthor(author: String): List<Book> {
        return books.filter {
            it.author
                .indexOf(author, 0, true) >= 0
        }
    }

    override fun findBooksByTitle(title: String): List<Book> {
        return books.filter {
            it.title
                .indexOf(title, 0, true) >= 0
        }
    }

    override fun findBooksByISBN(isbn: String): List<Book> {
        return books.filter {
            it.ISBN
                .indexOf(isbn, 0, true) >= 0
        }
    }


    override fun findBooksByLibraryItemId(id: String): Book? {
        return books.findLast {
            it.libraryItemId == id
        }
    }

    override fun updateBook(new: Book) {
        books = books.map {
            if (it.libraryItemId == new.libraryItemId) new else it
         }

   println(books)
    }

    override fun finBooksLoanedTo(userId: String): List<Book> {
       return books.filter { it.borrower?.id == userId }
    }
}