package com.knight.moonreaderdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface LightNovelDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBook(lightNovel: LightNovel)

    @Update
    suspend fun updateBook(lightNovel: LightNovel)

    @Query("SELECT * FROM `Series List`")
    fun getAllBook(): LiveData<List<LightNovel>>

    @Delete
    suspend fun deleteBook(lightNovel: LightNovel)

    @Query("SELECT * FROM `Series List` WHERE id LIKE :id")
    fun getOneBook(id: Int): LightNovel

    @Query("SELECT * FROM `Series List` WHERE id LIKE :id")
    fun getOneLiveBook(id: Int): LiveData<LightNovel>


}