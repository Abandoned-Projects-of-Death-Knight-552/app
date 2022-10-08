package com.knight.moonreaderdatabase.database

import androidx.lifecycle.LiveData

class BookRepository(private val dao: LightNovelDAO) {
    val allBooks: LiveData<List<LightNovel>> = dao.getAllBook()

    suspend fun insertBook(lightNovel: LightNovel) {
        dao.insertBook(lightNovel)
    }
     suspend fun updateBook(lightNovel: LightNovel) {
         dao.updateBook(lightNovel)
     }

    suspend fun deleteBook(lightNovel: LightNovel) {
        dao.deleteBook(lightNovel)
    }

    fun fetchBook(id: Int): LightNovel {
        return dao.getOneBook(id)
    }
}