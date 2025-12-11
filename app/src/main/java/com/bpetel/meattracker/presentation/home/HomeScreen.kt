package com.bpetel.meattracker.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bpetel.meattracker.presentation.home.component.GraphFilter
import com.bpetel.meattracker.presentation.home.component.TotalChart
import com.developerstring.jetco.ui.charts.barchart.ColumnBarChart
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val state = viewModel.homeState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dashboard",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }

        GraphFilter(viewModel, state.value)

        if (state.value.totalByPeriod.isNotEmpty()) {
            ColumnBarChart(
                modifier = Modifier.padding(16.dp),
                chartData = state.value.totalByPeriod
            )
            TotalChart(state.value.totalByMeatType)
        } else {
            Card(
                modifier = Modifier.height(200.dp).padding(8.dp),
                colors = CardDefaults.cardColors(Color.White)
            ) {
                Text(
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    text = "Aucune donnée"
                )
            }
        }
    }
}


