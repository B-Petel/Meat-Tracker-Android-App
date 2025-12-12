package com.bpetel.meattracker.domain.usecase

import com.bpetel.meattracker.domain.repository.MeatRepository
import com.bpetel.meattracker.presentation.utils.TimePeriod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.format.TextStyle
import java.time.temporal.WeekFields
import java.util.Locale

class GetTotalByPeriodUseCase(
    private val repository: MeatRepository
) {
    operator fun invoke(period: TimePeriod): Flow<Map<String, Float>> {
        val week = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear())
        val month = LocalDate.now().monthValue
        val year = LocalDate.now().year.toString()

        return when (period) {
            TimePeriod.WEEK -> repository.getTotalWeightByDayWeek(week).map {
                it.mapKeys { date ->
                    Instant.ofEpochMilli(date.key)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                        .dayOfWeek
                        .getDisplayName(TextStyle.SHORT, Locale.getDefault())
                }
            }

            TimePeriod.MONTH -> repository.getTotalWeightByDayMonth(week, month)
                .map { it.mapKeys { day -> day.key.toString() } }

            TimePeriod.YEAR -> repository.getTotalWeightByMonth(year).map {
                it.mapKeys { month ->
                    Month.of(month.key)
                        .getDisplayName(TextStyle.SHORT, Locale.getDefault())

                }
            }
        }
    }
}