package com.bpetel.meattracker.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun AddMeatEntryScreen() {

    var text by remember { mutableStateOf("") }

    Column(

    ) {
        TextField(
            value = text,
            onValueChange = { },
            label = {
                Text(
                    "Label"
                )
            }
        )

        Text(
            text = text
        )

        Button(
            onClick = { }
        ) {

        }
    }
}