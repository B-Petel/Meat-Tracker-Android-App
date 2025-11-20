package com.bpetel.meattracker.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.bpetel.meattracker.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HistoryScreen() {

    val viewModel: MainViewModel = koinViewModel()
    val state = viewModel.state.collectAsState()

    LazyColumn() {

        items(state.value) { meat ->
            Text(
                meat.type
            )
        }
    }
}