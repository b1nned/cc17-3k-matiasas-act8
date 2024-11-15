package com.example.bookshelf.api

import com.example.bookshelf.data.Book


data class BooksResponse(
    val items: List<Book>?
)