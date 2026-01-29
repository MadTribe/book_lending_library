package com.patchwork.booklibrary.acceptancetests
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import com.patchwork.booklibrary.services.BorrowResult
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe

class BorrowBooksAcceptanceTest : BehaviorSpec({

    given("a library with books and some users") {
        val fixtures = TestFixturesFactory.create()
        val library = fixtures.bookLibrary;
        val user1 = fixtures.users[0]

        `when`("a user borrows an available non-reference book") {
            val borrowResult = library.borrow(user1.id, "001")

            then("the borrow will be successful") {
                borrowResult shouldBe BorrowResult.Success
            }
        }

    }
})