package com.example.myapp.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.myapp.R
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
            .background(color = Color(R.color.green_and_white))
            .fillMaxSize()
    ) {

        TopAppBar(
            modifier = Modifier
                .background(color = Color.White)
                .height(80.dp)
        ) {
            Row() {
                Image(
                    bitmap = ImageBitmap.imageResource(R.drawable.back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { navController.navigate(route = Screen.Country.route) }
                        .size(50.dp)
                        .padding(top = 15.dp)
                )
                Text(
                    text = country,
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 15.dp),
                    fontSize = 7.em,
                    textAlign = TextAlign.Center
                )
            }

        }

        Surface(
            modifier = Modifier
                .background(color = Color.Gray)
                .fillMaxSize()
        ) {

            Card(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(color = Color.White)
                        .padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = "Date: ${summaryItem?.date ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "Confirmed: ${summaryItem?.confirmed ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "Recovered: ${summaryItem?.recovered ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "Deaths: ${summaryItem?.deaths ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "Total Confirmed: ${summaryItem?.totalConfirmed ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "Total Deaths: ${summaryItem?.totalDeaths ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                    Text(
                        text = "totalRecovered: ${summaryItem?.totalRecovered ?: "нет данных"}",
                        modifier = Modifier
                            .padding(vertical = 5.dp),
                        fontSize = 5.em,
                        color = Color.Gray
                    )
                }

            }
        }
    }
}