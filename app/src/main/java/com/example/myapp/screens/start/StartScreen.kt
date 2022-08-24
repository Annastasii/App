package com.example.myapp.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.screens.Screen


@Composable
fun StartScreen(
    navController: NavController
) {
    val startViewModel = hiltViewModel<StartViewModel>()
    val listCountry = startViewModel.countryItem
    val textState = remember { mutableStateOf(TextFieldValue("")) }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Заболеваемость COVID",
            modifier = Modifier.padding(vertical = 10.dp),
            fontSize = 7.em
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 8.dp,
        ) {
            Column() {
                TextField(
                    value = textState.value, modifier = Modifier
                        .padding(vertical = 15.dp, horizontal = 50.dp),
                    onValueChange = { value ->
                        textState.value = value
                    },
                    label = {
                        Text(text = "Поиск")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Search
                    )
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val countries = listCountry.value
                    var filteredCountries: List<CountryEntity>
                    val searchedText = textState.value.text
                    filteredCountries = if (searchedText.isEmpty()) {
                        countries
                    } else {
                        countries.let { list ->
                            list.filter { item ->
                                item.country.contains(searchedText)
                            }
                        }
                    }
                    itemsIndexed(
                        filteredCountries
                    ) { _, item ->
                        Card() {
                            Text(text = item.country,
                                fontSize = 4.em,
                                modifier = Modifier
                                    .padding(vertical = 15.dp)
                                    .clickable {
                                        navController.navigate(route = "${Screen.Info.route}/${item.country}")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewStartScreen() {
    StartScreen(navController = rememberNavController())
}

