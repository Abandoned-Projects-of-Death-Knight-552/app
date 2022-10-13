package com.knight.moonreaderdatabase.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Series List")
data class LightNovel (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "synopsis") val synopsis: String?,
    @ColumnInfo(name = "download") val download: String?,
    @ColumnInfo(name = "coverURL") val coverRemote: String?,
    @ColumnInfo(name = "coverPath") val coverLocal: String?,
        ): Parcelable