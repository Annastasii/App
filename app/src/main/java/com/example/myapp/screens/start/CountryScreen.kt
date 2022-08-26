package com.example.myapp.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.myapp.R
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.screens.Screen


@Composable
fun CountryScreen(
    navController: NavController
) {
    val startViewModel = hiltViewModel<StartViewModel>()
    val listCountry = startViewModel.countryItem
    val textState = remember { mutableStateOf(TextFieldValue("")) }


    Column(
        modifier = Modifier
            .background(color = Color(R.color.green_and_white))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 8.dp,
        ) {
            Column() {
                TopAppBar(modifier = Modifier
                    .background(color = Color.White)
                    .height(80.dp)) {
                    TextField(
                        value = textState.value, modifier = Modifier
                            .padding(vertical = 15.dp, horizontal = 50.dp),
                        onValueChange = { value ->
                            textState.value = value
                        },
                        label = {
                            Text(text = "Search...",
                            color = Color.Gray)
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        )
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp),
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
                        Card(
                            modifier = Modifier
                                .width(330.dp)
                                .clip(shape = RoundedCornerShape(15.dp))
                                .padding(vertical = 5.dp),
                            backgroundColor = Color(R.color.green)
                        ) {
                            Text(text = item.country,
                                fontSize = 4.em,
                                color = Color.White,
                                modifier = Modifier
                                    .padding(vertical = 15.dp, horizontal = 5.dp)
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
    CountryScreen(navController = rememberNavController())
}

