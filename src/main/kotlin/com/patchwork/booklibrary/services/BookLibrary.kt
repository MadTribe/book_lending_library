package com.patchwork.booklibrary.services

import com.patchwork.booklibrary.model.Book

interface BookLibrary {
    fun findBooksByAuthor(string: String) : List<Book>

}