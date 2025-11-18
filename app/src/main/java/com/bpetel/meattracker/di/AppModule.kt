package com.bpetel.meattracker.di

import androidx.room.Room
import com.bpetel.meattracker.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.koinApplication
import org.koin.dsl.module

var appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
}