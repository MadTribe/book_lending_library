package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book

interface BookLibrary {
    fun findBooksByAuthor(author: String) : List<Book>
    fun findBooksByTitle(title: String) : List<Book>
    fun findBooksByISBN(isbn: String) : List<Book>

}