package com.bpetel.meattracker.presentation.home.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.developerstring.jetco.ui.charts.piechart.PieChart
import com.developerstring.jetco.ui.charts.piechart.config.PieChartDefaults

@Composable
fun TotalChart(
    state: Map<String, Float>
) {
    Card(
        modifier = Modifier
            .height(500.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        PieChart(
            modifier = Modifier.fillMaxSize(),
            chartData = state,
            pieChartConfig = PieChartDefaults.pieChartConfig()
        )
    }
}