package com.bpetel.meattracker.di

import androidx.room.Room
import com.bpetel.meattracker.MainViewModel
import com.bpetel.meattracker.data.AppDatabase
import com.bpetel.meattracker.data.LocalRepositoryImpl
import com.bpetel.meattracker.domain.LocalRepository
import com.bpetel.meattracker.domain.usecase.DeleteMeatEntryUseCase
import com.bpetel.meattracker.domain.usecase.GetMeatHistoryPerDateUseCase
import com.bpetel.meattracker.domain.usecase.SaveMeatEntryUseCase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

var appModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            "app-database"
        ).build()
    }

    single<LocalRepository> { LocalRepositoryImpl(get()) }

    factory { GetMeatHistoryPerDateUseCase(get()) }

    factory { SaveMeatEntryUseCase(get()) }

    factory { DeleteMeatEntryUseCase(get()) }

    viewModel { MainViewModel(get(), get(), get()) }
}