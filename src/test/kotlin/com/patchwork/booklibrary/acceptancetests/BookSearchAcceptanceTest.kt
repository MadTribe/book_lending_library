package com.patchwork.booklibrary.acceptancetests
import com.patchwork.booklibrary.fixtures.TestFixturesFactory
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain

class BookSearchAcceptanceTest : BehaviorSpec({

    given("a library with books") {
        val fixtures = TestFixturesFactory.create()
        val library = fixtures.bookLibrary;

        `when`("a user searches for books by author") {
            val results = library.findBooksByAuthor("Terry Pratchett")

            then("matching books are returned") {
                results.map { it.title } shouldContain "Guards! Guards!"
                results.map { it.title } shouldContain "Weird Sisters"
            }
        }
        `when`("a user searches for books by title") {
            val results = library.findBooksByTitle("Weird Sisters")

            then("matching books are returned") {
                results.map { it.title } shouldContain "Weird Sisters"
            }
        }
    }
})