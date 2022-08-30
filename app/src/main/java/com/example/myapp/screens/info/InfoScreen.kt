package com.example.myapp.screens.info

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
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
    infoViewModel.setSummary(country!!)
    val summary = infoViewModel.summary
    val summaryItem = summary.value.getOrNull(0)
    val isConnection = infoViewModel.isConnection

    infoViewModel.isConnection(LocalContext.current)

    // проверка подключения к интернету
    if (isConnection.value) {
        infoViewModel.setSummary(country)
    } else {
        Toast.makeText(LocalContext.current, "No server", Toast.LENGTH_LONG).show()
        infoViewModel.getSummary(country)
    }

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
                    text = stringResource(id = R.string.country, country),
                    modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 15.dp),
                    fontSize = 7.em,
                    textAlign = TextAlign.Center
                )
            }

        }

        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White)
                    .padding(horizontal = 20.dp)
            ) {
                InfoText(
                    data = stringResource(
                        id = R.string.date,
                        summaryItem?.date ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.confirmed,
                        summaryItem?.confirmed ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.recovered,
                        summaryItem?.recovered ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.deaths,
                        summaryItem?.deaths ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.total_confirmed,
                        summaryItem?.totalConfirmed ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.total_deaths,
                        summaryItem?.totalDeaths ?: stringResource(R.string.no_data)
                    )
                )
                InfoText(
                    data = stringResource(
                        id = R.string.total_recovered,
                        summaryItem?.totalRecovered ?: stringResource(R.string.no_data)
                    )
                )
            }
        }
    }
}