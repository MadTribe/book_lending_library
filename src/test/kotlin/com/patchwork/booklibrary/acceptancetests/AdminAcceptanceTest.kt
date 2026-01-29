package com.patchwork.booklibrary.acceptancetests
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.services.BorrowError
import com.patchwork.booklibrary.services.BorrowResult
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class AdminAcceptanceTest : BehaviorSpec({

    given("a library with books and some users") {
        val fixtures = TestFixturesFactory.create()
        val library = fixtures.bookLibrary;
        val adminInterface = fixtures.adminService;
        val user1 = fixtures.users[0]
        val user2 = fixtures.users[1]

        `when`("a users borrow an available books") {
            val borrowResult = library.borrow(user1.id, "0001")
            val borrowResult2 = library.borrow(user1.id, "0002")

            then("admin report lists all borrowed books"){
                val borrowed = adminInterface.reportBorrowedBooks()

                borrowed.map { it.title } shouldContain "Guards! Guards!"
                borrowed.map { it.title } shouldContain "Weird sisters"
            }
        }

    }
})