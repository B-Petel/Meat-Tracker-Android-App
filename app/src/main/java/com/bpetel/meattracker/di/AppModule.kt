package com.bpetel.meattracker.di

import androidx.room.Room
import com.bpetel.meattracker.data.AppDatabase
import com.bpetel.meattracker.data.repository.MeatRepositoryImpl
import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.domain.usecase.GetTotalByPeriodUseCase
import com.bpetel.meattracker.presentation.history.HistoryViewModel
import com.bpetel.meattracker.presentation.home.HomeViewModel
import com.bpetel.meattracker.presentation.stats.StatsViewModel
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

    single<MeatRepository> { MeatRepositoryImpl(get()) }

    factory { GetTotalByPeriodUseCase(get()) }

    viewModel { HomeViewModel(get(), get()) }

    viewModel { StatsViewModel(get()) }

    viewModel { HistoryViewModel(get()) }
}
