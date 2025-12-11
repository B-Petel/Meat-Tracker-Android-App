package com.bpetel.meattracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bpetel.meattracker.data.dao.MeatDao
import com.bpetel.meattracker.data.dao.UserDao
import com.bpetel.meattracker.data.model.MeatEntity
import com.bpetel.meattracker.data.model.UserEntity

@Database(entities = [UserEntity::class, MeatEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun meatDao(): MeatDao
}