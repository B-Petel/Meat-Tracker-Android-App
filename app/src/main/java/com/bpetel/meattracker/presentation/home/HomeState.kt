package com.bpetel.meattracker.presentation.home

import com.bpetel.meattracker.presentation.utils.TimePeriod

data class HomeState(
    val period:  TimePeriod = TimePeriod.WEEK,
    val filteredData: Map<Int, Int> = emptyMap()
)
