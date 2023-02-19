package com.example.myapp.screens.info

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em

@Composable
fun InfoText(
    data: String
) {
    Text(
        text = data,
        modifier = Modifier
            .padding(vertical = 5.dp),
        fontSize = 5.em,
        color = Color.Gray
    )
}