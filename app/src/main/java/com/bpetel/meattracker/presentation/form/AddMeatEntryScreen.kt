package com.bpetel.meattracker.presentation.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bpetel.meattracker.MainViewModel
import com.bpetel.meattracker.presentation.utils.MeatType
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddMeatEntryScreen(
    id: Int?,
    type: String,
    parts: String,
    weight: String,
    onSubmit: () -> Unit
) {
    val viewModel: MainViewModel = koinViewModel()
    var typeExpanded by remember { mutableStateOf(false) }
    val stateVertical = rememberScrollState(0)
    var type by remember { mutableStateOf(type) }
    var parts by remember { mutableStateOf(parts) }
    var weight by remember { mutableStateOf(weight) }
    var weightExpanded by remember { mutableStateOf(false) }
    var unit by remember { mutableStateOf("g") }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            OutlinedTextField (
                modifier = Modifier.fillMaxWidth(),
                value = type,
                onValueChange = { type = it },
                label = { Text("Type") },
                readOnly = true,
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        ""
                    )
                    DropdownMenu (
                        expanded = typeExpanded,
                        onDismissRequest = { typeExpanded = false },
                        scrollState = stateVertical,
                        modifier = Modifier
                            .width(200.dp)
                            .height(200.dp)
                    ) {
                        MeatType.entries.forEach {
                            DropdownMenuItem(
                                text = { Text(it.type) },
                                onClick = {
                                    type = it.type
                                    typeExpanded = !typeExpanded
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            )

            Box(
                modifier = Modifier.matchParentSize().clickable { typeExpanded = !typeExpanded }
            )
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = parts,
            onValueChange = { parts = it },
            label = {
                Text(
                    "Morceau"
                )
            }
        )

        Box {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = weight,
                onValueChange = { weight = it },
                label = {
                    Text(
                        "Weight"
                    )
                },
                trailingIcon = {
                    Icon(
                        Icons.Default.ArrowDropDown,
                        "",
                        Modifier.clickable { weightExpanded = !weightExpanded }
                    )
                    DropdownMenu (
                        expanded = weightExpanded,
                        onDismissRequest = { weightExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("g") },
                            onClick = {
                                unit = "g"
                                weightExpanded = !weightExpanded
                            }
                        )
                        HorizontalDivider()
                        DropdownMenuItem(
                            text = { Text("kg") },
                            onClick = {
                                unit = "kg"
                                weightExpanded = !weightExpanded
                            }
                        )
                    }
                },
                suffix = { Text(unit) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            )
        }

        Button(
            onClick = {
                viewModel.onSubmit(
                    FormState(
                        id = id,
                        type = type,
                        mealPart = parts,
                        weightInGrams = weight
                    )
                )
                onSubmit()
            }
        ) {
            Text("Submit")
        }
    }
}