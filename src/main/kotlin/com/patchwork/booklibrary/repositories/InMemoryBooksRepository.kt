package com.patchwork.booklibrary.repositories

import com.patchwork.booklibrary.model.Book
import kotlin.reflect.KClass


fun caseInsensitiveMatchAnywhere(fieldName: String, data: List<Book>): (searchTerm: String) -> List<Book> {

    val getProp = fun(b: Book): String {

        val t = b.javaClass
            .declaredFields
            .find { it.name == fieldName }
            ?.also { it.trySetAccessible() }
            ?.get(b)

        return t.toString()
    }

    return fun(searchTerm: String): List<Book> {
        return data.filter {
            getProp(it)
                .indexOf(searchTerm, 0, true) >= 0
        }
    }
}

class InMemoryBooksRepository(var books: List<Book>) : BooksRepository {
    val findBooksByAuthorImpl: (author: String) -> List<Book> = caseInsensitiveMatchAnywhere("author", books)
    val findBooksByTitleImpl: (author: String) -> List<Book> = caseInsensitiveMatchAnywhere("title", books)

    override fun findBooksByAuthor(author: String): List<Book> {
        return findBooksByAuthorImpl(author)
    }

    override fun findBooksByTitle(title: String): List<Book> {
        return findBooksByTitleImpl(title)
    }

    override fun findBooksByISBN(isbn: String): List<Book> {
        return books.filter {
            it.ISBN == isbn
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
    }

    override fun findBooksLoanedTo(userId: String): List<Book> {
        return books.filter { it.borrower?.id == userId }
    }

    override fun findBorrowedBooks(): List<Book> {
        return books.filter { it.borrower != null }
    }
}