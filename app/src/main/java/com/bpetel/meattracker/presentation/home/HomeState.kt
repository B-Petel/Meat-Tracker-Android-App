package com.bpetel.meattracker.presentation.home

import com.bpetel.meattracker.presentation.utils.TimePeriod

data class HomeState(
    val periodFilter:  TimePeriod = TimePeriod.WEEK,
    val totalByMeatType: Map<String, Float> = emptyMap(),
    val totalByPeriod: Map<String, Float> = emptyMap()
)
