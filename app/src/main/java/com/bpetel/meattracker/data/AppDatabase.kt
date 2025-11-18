package com.bpetel.meattracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [User::class, Meat::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun meatDao(): MeatDao
}