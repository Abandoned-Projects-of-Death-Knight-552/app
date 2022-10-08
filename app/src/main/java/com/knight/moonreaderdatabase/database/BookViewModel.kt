package com.knight.moonreaderdatabase.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BookViewModel(application: Application): AndroidViewModel(application) {
    private val repository: BookRepository
    val allBooks: LiveData<List<LightNovel>>

    init {
        val dao = MainDatabase.getDatabase(application).bookDAO()
        repository = BookRepository(dao)
        allBooks = repository.allBooks
    }

    fun insertBook(lightNovel: LightNovel) {
        viewModelScope.launch {
            repository.insertBook(lightNovel)
        }
    }
    fun updateBook(lightNovel: LightNovel) {
        viewModelScope.launch {
            repository.updateBook(lightNovel)
        }
    }

    fun deleteBook(lightNovel: LightNovel) {
        viewModelScope.launch {
            repository.deleteBook(lightNovel)
        }
    }

    fun fetchBook(id: Int): LightNovel {
        return repository.fetchBook(id)
    }
}