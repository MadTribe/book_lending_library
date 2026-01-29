package com.patchwork.booklibrary.acceptancetests
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.services.BorrowError
import com.patchwork.booklibrary.services.BorrowResult
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class BorrowBooksAcceptanceTest : BehaviorSpec({

    given("a library with books and some users") {
        val fixtures = TestFixturesFactory.create()
        val library = fixtures.bookLibrary;
        val user1 = fixtures.users[0]
        val user2 = fixtures.users[1]

        `when`("a user borrows an available non-reference book") {
            val borrowResult = library.borrow(user1.id, "0001")

            then("the borrow will be successful") {
                borrowResult shouldBe BorrowResult.Success
            }
            val user1Loans = library.userLoans(user1.id)
            then("the book will appear in their borrow list"){

                user1Loans.map { it.title } shouldContain "Weird sisters"
            }

            then("other users can't borrow the same book"){

                val borrowResult2 = library.borrow(user2.id, "0001")
                borrowResult2 shouldBe BorrowResult.Failure(BorrowError.AlreadyBorrowed)
            }
        }

    }
})