package com.bpetel.meattracker.presentation.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bpetel.meattracker.presentation.home.HomeState
import com.bpetel.meattracker.presentation.home.HomeViewModel
import com.bpetel.meattracker.presentation.utils.TimePeriod

@Composable
fun GraphFilter(
    viewModel: HomeViewModel,
    state: HomeState
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                modifier = Modifier.clickable(
                    onClick = { viewModel.onFilterClick(TimePeriod.WEEK) }
                ),
                text = "Week",
                fontWeight = if (state.periodFilter == TimePeriod.WEEK) FontWeight.Bold
                else FontWeight.Light
            )
            Text(
                modifier = Modifier.clickable(
                    onClick = { viewModel.onFilterClick(TimePeriod.MONTH) }
                ),
                text = "Month",
                fontWeight = if (state.periodFilter == TimePeriod.MONTH) FontWeight.Bold
                else FontWeight.Light
            )
            Text(
                modifier = Modifier.clickable(
                    onClick = { viewModel.onFilterClick(TimePeriod.YEAR) }
                ),
                text = "Year",
                fontWeight = if (state.periodFilter == TimePeriod.YEAR) FontWeight.Bold
                else FontWeight.Light
            )
        }
    }
}