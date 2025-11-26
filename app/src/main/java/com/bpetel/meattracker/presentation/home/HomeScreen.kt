package com.bpetel.meattracker.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.homeState.collectAsState()

    Column {
        state.value.forEach {
            Text(
                "Date : ${it.key}"
            )
            if (it.value <= 1000) {
                Text(
                    "Total : ${it.value} g"
                )
            } else {
                Text(
                    "Total : ${it.value / 1000f} kg"
                )
            }
        }
    }
}