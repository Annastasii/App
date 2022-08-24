package com.example.myapp.screens.info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapp.screens.Screen


@Composable
fun InfoScreen(
    navController: NavController,
    country: String?
) {
    val infoViewModel = hiltViewModel<InfoViewModel>()
    infoViewModel.setConfirmed(country!!)
    val summary = infoViewModel.summary
    val summaryItem = summary.value.getOrNull(0)
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Text(
            text = country,
            modifier = Modifier
                .padding(vertical = 15.dp),
            fontSize = 7.em,
            textAlign = TextAlign.Center
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Date: ${summaryItem?.date ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "Confirmed: ${summaryItem?.confirmed ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "Recovered: ${summaryItem?.recovered ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "Deaths: ${summaryItem?.deaths ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "Total Confirmed: ${summaryItem?.totalConfirmed ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "Total Deaths: ${summaryItem?.totalDeaths ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
            Text(
                text = "totalRecovered: ${summaryItem?.totalRecovered ?: "нет данных"}",
                modifier = Modifier
                    .padding(vertical = 5.dp),
                fontSize = 5.em
            )
        }
        Button(onClick = { navController.navigate(route = Screen.Start.route) }) {
            Text(text = "Back")
        }
    }
}