package com.example.myapp.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
            .background(colorResource(R.color.green_and_white))
            .fillMaxSize()
    ) {

        TopAppBar(
            modifier = Modifier
                .height(80.dp),
            backgroundColor = colorResource(R.color.dark_green)
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_keyboard_arrow_left_24),
                    contentDescription = "Back",
                    modifier = Modifier
                        .clickable { navController.navigate(route = Screen.Country.route) }
                        .size(50.dp)
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
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "Date: ${summaryItem?.date ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Confirmed: ${summaryItem?.confirmed ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Recovered: ${summaryItem?.recovered ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Deaths: ${summaryItem?.deaths ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Total Confirmed: ${summaryItem?.totalConfirmed ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Total Deaths: ${summaryItem?.totalDeaths ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )
                Text(
                    text = "Total Recovered: ${summaryItem?.totalRecovered ?: stringResource(R.string.nodata)}",
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    fontSize = 5.em,
                    color = Color.Gray
                )

            }
        }
    }
}