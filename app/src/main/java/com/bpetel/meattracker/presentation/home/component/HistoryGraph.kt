package com.bpetel.meattracker.presentation.home.component

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.bpetel.meattracker.presentation.home.HomeState
import com.bpetel.meattracker.presentation.utils.TimePeriod
import java.time.DayOfWeek
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun HistoryGraph(
    state: HomeState
) {
    // 1. Convertir la Map en liste de points (Offset)
    val points = remember(state.filteredData) {
        state.filteredData.map { (x, y) -> Offset(x.toFloat(), y.toFloat()) }
    }

    // 2. Calculer les bornes pour normaliser les coordonnées
    val xMin = remember(points) { points.minOfOrNull { it.x } ?: 0f }
    val xMax = remember(points) { points.maxOfOrNull { it.x } ?: 0f }
    val yMin = remember(points) { points.minOfOrNull { it.y } ?: 0f }
    val yMax = remember(points) { points.maxOfOrNull { it.y } ?: 0f }

    // 3. Normaliser les points pour qu'ils tiennent dans le Canvas
    val normalizedPoints = remember(points, xMin, xMax, yMin, yMax) {
        points.map { point ->
            val normalizedX = if (xMax != xMin) {
                (point.x - xMin) / (xMax - xMin)
            } else {
                0.5f // Si tous les x sont identiques
            }
            val normalizedY = if (yMax != yMin) {
                1f - (point.y - yMin) / (yMax - yMin) // Inverser Y pour que 0 soit en bas
            } else {
                0.5f // Si tous les y sont identiques
            }
            Offset(normalizedX, normalizedY)
        }
    }

    Card(
        modifier = Modifier.height(200.dp).padding(8.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            val padding = 50f
            val paddingPath = 50f

            drawLine(
                color = Color.Black,
                start = Offset(padding, canvasHeight - padding),
                end = Offset(canvasWidth - padding, canvasHeight - padding),
                strokeWidth = 2f
            )

            val xStep = (canvasWidth - 2 * padding) / (state.period.i-1)

            for (i in 0..<state.period.i) {
                val x = padding + i * xStep
                drawContext.canvas.nativeCanvas.drawText(
                    when (state.period) {
                        TimePeriod.WEEK -> getFirstLetterOfDay(i+1)
                        TimePeriod.MONTH -> (i+1).toString()
                        TimePeriod.YEAR -> getFirstMonthLetter(i+1)
                    },
                    if (state.period == TimePeriod.MONTH) x else x - 15,
                    canvasHeight - padding + 30,
                    Paint().apply {
                        textSize = 20f
                        color = android.graphics.Color.BLACK
                    }
                )
            }

            // a. Dessiner le Path (ligne brisée)
            val path = Path().apply {
                normalizedPoints.forEachIndexed { index, _ ->
                    if (index == 0) return@forEachIndexed // On saute le premier point

                    val cx = paddingPath + (index-1) * xStep
                    val cy = (canvasHeight - paddingPath) - (1 - normalizedPoints[index-1].y) * (canvasHeight - 2 * padding)
                    val nx = paddingPath + index * xStep
                    val ny = (canvasHeight - paddingPath) - (1 - normalizedPoints[index].y) * (canvasHeight - 2 * padding)

                    val x1 = (nx + cx) / 2
                    val y1 = cy
                    val x2 = (nx + cx) / 2
                    val y2 = ny

                    if (index-1 == 0) {
                        moveTo(cx, cy)
                    } else {
                        cubicTo(x1,y1,x2,y2,nx,ny)
                    }
                }
            }

            drawPath(
                path,
                color = Color.Red,
                style = Stroke(width = 3f)
            )
        }
    }
}

fun getFirstLetterOfDay(dayNumber: Int): String {
    val dayOfWeek = DayOfWeek.of(dayNumber)
    return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.FRENCH).toString()
}

fun getFirstMonthLetter(monthNumber: Int): String {
    val monthOfYear = Month.of(monthNumber)
    return monthOfYear.getDisplayName(TextStyle.SHORT, Locale.FRENCH).toString()
}