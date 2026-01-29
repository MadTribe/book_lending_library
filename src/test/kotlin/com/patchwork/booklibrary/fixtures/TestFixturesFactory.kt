package com.patchwork.booklibrary.fixtures

import com.patchwork.booklibrary.model.Book
import com.patchwork.booklibrary.model.User
import com.patchwork.booklibrary.repositories.InMemoryBooksRepository
import com.patchwork.booklibrary.repositories.InMemoryUserRepository
import com.patchwork.booklibrary.services.AdminService
import com.patchwork.booklibrary.services.AdminServiceImpl
import com.patchwork.booklibrary.services.BookLibrary
import com.patchwork.booklibrary.services.BookLibraryImpl

val books = listOf(
    Book("0001", "Terry Pratchett","Weird sisters" , ISBN = "9780142428948"),
    Book("0002", "Terry Pratchett","Guards! Guards!", ISBN = "978014200001"),
    Book("0003", "Oxford University Press","Encyclopedia of Birds", ISBN = "234567896987", referenceBook = true),
)
val users = listOf(User("0001", "sally.smith"),
                  User("0002", "joe.bloggs"))

open class TestFixturesFactory() {
    companion object {
        fun create() : TestFixtures {
            val booksRepository = InMemoryBooksRepository(books)
            val bookLibrary = BookLibraryImpl(booksRepository,
                InMemoryUserRepository(users))

            val adminService = AdminServiceImpl(booksRepository)
            return TestFixtures(bookLibrary, users, adminService )
        }
    }

    fun books(): List<Book> {
        return books
    }

    fun users(): List<User> {
        return users
    }
}

class TestFixtures(val bookLibrary: BookLibrary, val users: List<User>, val adminService: AdminService) {

}
