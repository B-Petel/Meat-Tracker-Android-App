//package com.bpetel.meattracker.presentation.home.component
//
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.drawscope.Stroke
//import androidx.compose.ui.unit.dp
//import com.bpetel.meattracker.presentation.home.HomeState
//
//@Composable
//fun HistoryGraph(
//    state: HomeState
//) {
//    val padding = 50f
//
//    Card(
//        modifier = Modifier
//            .height(200.dp)
//            .padding(8.dp),
//        colors = CardDefaults.cardColors(Color.White)
//    ) {
//        Canvas(modifier = Modifier.fillMaxSize()) {
//            val spacePerData = (size.width - padding) / state.totalByPeriod.size
//            val xMax = state.totalByPeriod.maxOfOrNull { it.key } ?: 0f
//            val xMin = state.totalByPeriod.minOfOrNull { it.key } ?: 0f
//            val yMax = state.totalByPeriod.maxOfOrNull { it.value } ?: 0f
//            val yMin = state.totalByPeriod.minOfOrNull { it.value } ?: 0f
//
//            drawLine(
//                color = Color.Black,
//                start = Offset(padding, size.height - padding),
//                end = Offset(padding, padding),
//                strokeWidth = 2f
//            )
//
//            drawLine(
//                color = Color.Black,
//                start = Offset( padding, size.height - padding),
//                end = Offset(size.width - padding, size.height - padding),
//                strokeWidth = 2f
//            )
//
//            state.totalByPeriod.forEach {
//                val x = ((size.width / xMax) * (it.key - 1)) + padding
//                val y = ((size.height / yMax.toFloat()) * (yMax.toFloat() - it.value)) + padding
//
//                drawCircle(
//                    color = Color.Red,
//                    radius = 10f,
//                    center = Offset(x, y)
//                )
//            }
//
//            val path = Path().apply {
//                state.totalByPeriod.forEach {
//                    val x = ((size.width / xMax.toFloat()) * (it.key - 1)) + padding
//                    val y = ((size.height / yMax.toFloat()) * (yMax.toFloat() - it.value)) + padding
//
//                    if (it.key == 1) {
//                        moveTo(x, y)
//                    } else {
//                        lineTo(x,y)
//                    }
//                }
//            }
//
//            drawPath(
//                path,
//                color = Color.Red,
//                style = Stroke(width = 3f)
//            )
//        }
//    }
//}
//