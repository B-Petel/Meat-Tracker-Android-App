package com.bpetel.meattracker.presentation.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.bpetel.meattracker.MainViewModel
import com.bpetel.meattracker.data.Meat
import com.bpetel.meattracker.presentation.utils.GetMeatIcon
import com.bpetel.meattracker.presentation.utils.LocalDateToRelativeDateString.toRelativeDateString
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate
import java.util.Locale.getDefault
import kotlin.math.roundToInt

@Composable
fun HistoryScreen(
    onEdit: (Meat) -> Unit
) {
    val viewModel: MainViewModel = koinViewModel()
    val state = viewModel.state.collectAsState()

    HistoryContent(onEdit,viewModel, state.value)
}

@Composable
fun HistoryContent(
    onEdit: (Meat) -> Unit,
    viewModel: MainViewModel,
    state: Map<LocalDate, List<Meat>>
) {
    LazyColumn {
        state.forEach { map ->
            item {
                Text(map.key.toRelativeDateString())
                HorizontalDivider()
            }

            items(map.value) { meat ->
                HistoryDraggableBox(onEdit,viewModel, meat)
            }
        }
    }
}

@Composable
fun HistoryDraggableBox(
    onEdit: (Meat) -> Unit,
    viewModel: MainViewModel,
    meat: Meat
) {
    val density = LocalDensity.current
    val offsetSize = 200.dp

    val anchors = remember {
        DraggableAnchors {
            DragAnchors.Start at 0f
            DragAnchors.End at -with(density) { offsetSize.toPx() }
        }
    }

    val dragState = remember {
        AnchoredDraggableState (
            initialValue = DragAnchors.Start,
            anchors = anchors
        ).apply {
            val newAnchors = with(density) {
                DraggableAnchors {
                    DragAnchors.Start at 0.dp.toPx()
                    DragAnchors.End at -offsetSize.toPx()
                }
            }
            updateAnchors(newAnchors)
        }
    }
    Box(
        modifier = Modifier.padding(bottom = 8.dp)
    ) {
        HistoryItem(meat = meat, dragState = dragState)
        HistoryDragItem(onEdit,viewModel, meat, dragState = dragState, offsetSize = offsetSize)
    }
}

@Composable
fun HistoryItem(
    meat: Meat,
    dragState: AnchoredDraggableState<DragAnchors>
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .offset {
                IntOffset(
                    dragState.requireOffset().roundToInt(), 0
                )
            }.anchoredDraggable(dragState, Orientation.Horizontal),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(GetMeatIcon.getMeatIcon(meat.type.lowercase(getDefault()))),
            contentDescription = "",
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(
                text = meat.type
            )
            Text(
                meat.meatPart
            )
            Text(
                meat.weightInGrams.toString()
            )
        }
    }
}

@Composable
fun BoxScope.HistoryDragItem(
    onEdit: (Meat) -> Unit,
    viewModel: MainViewModel,
    meat: Meat,
    dragState: AnchoredDraggableState<DragAnchors>,
    offsetSize: Dp
) {
    val scope = rememberCoroutineScope()

    Box (
        modifier = Modifier
            .width(100.dp)
            .align(Alignment.CenterEnd)
            .offset {
                IntOffset(
                    (dragState.requireOffset() + offsetSize.toPx()).roundToInt(), 0
                )
            }
            .anchoredDraggable(dragState, Orientation.Horizontal)
    ) {
        Row {
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = {
                    scope.launch {
                        onEdit(meat)
                        //viewModel.toggleDialog(true)
                        dragState.animateTo(DragAnchors.Start)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.fillMaxHeight(),
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(
                modifier = Modifier.fillMaxHeight(),
                onClick = {
                    scope.launch {
                        viewModel.onDelete(meat)
                        dragState.animateTo(DragAnchors.Start)
                    }
                }
            ) {
                Icon(
                    modifier = Modifier.fillMaxHeight(),
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Supprimer"
                )
            }
        }
    }
}
