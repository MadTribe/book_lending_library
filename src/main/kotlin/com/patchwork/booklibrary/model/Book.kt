package com.patchwork.booklibrary.model

data class Book(
    val libraryItemId: String,
    val author: String,
    val title: String,
    val ISBN: String,
    val referenceBook: Boolean = false,
    val borrower: User? = null){
}
