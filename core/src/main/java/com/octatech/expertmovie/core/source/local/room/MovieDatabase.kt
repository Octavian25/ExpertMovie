package com.octatech.expertmovie.core.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.octatech.expertmovie.core.source.local.entity.MovieEntity
import com.octatech.expertmovie.core.source.local.entity.SeriesEntity

@Database(entities = [MovieEntity::class, SeriesEntity::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}