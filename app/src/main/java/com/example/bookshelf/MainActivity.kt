// MainActivity.kt
package com.example.bookshelf

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelf.adapter.BookAdapter
import com.example.bookshelf.databinding.ActivityMainBinding
import com.example.bookshelf.ui.BooksViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BooksViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        viewModel.books.observe(this) { books ->
            recyclerView.adapter = BookAdapter(books)
        }

        val searchButton: ImageButton = binding.searchButton
        val searchEditText: EditText = binding.searchEditText

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.fetchBooks(query)
            }
        }
    }
}