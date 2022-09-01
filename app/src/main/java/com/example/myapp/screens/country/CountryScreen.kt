package com.example.myapp.screens.country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapp.R


@Composable
fun CountryScreen(
    navController: NavController
) {
    val countryViewModel = hiltViewModel<CountryViewModel>()
    val listCountry = countryViewModel.countryItem.value
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    countryViewModel.getCountry(LocalContext.current)

    Column(
        modifier = Modifier
            .background(colorResource(R.color.green_and_white))
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            elevation = 8.dp,
        ) {
            Column {
                TopAppBar(
                    modifier = Modifier
                        .height(80.dp),
                    backgroundColor = colorResource(R.color.dark_green)

                ) {
                    OutlinedTextField(
                        value = textState.value, modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        onValueChange = { value ->
                            textState.value = value
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.search),
                                color = Color.White
                            )
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            cursorColor = Color.White,
                            textColor = Color.White,
                            focusedBorderColor = colorResource(R.color.white),
                            unfocusedBorderColor = colorResource(R.color.dark_green)
                        ),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(R.drawable.ic_baseline_search_24),
                                tint = Color.White,
                                contentDescription = "search"
                            )
                        }
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val searchedText = textState.value.text
                    val filteredCountries = if (searchedText.isEmpty()) {
                        listCountry
                    } else {
                        listCountry.let { list ->
                            list.filter { item ->
                                item.country.contains(searchedText)
                            }
                        }
                    }
                    itemsIndexed(
                        filteredCountries
                    ) { _, item ->
                        CardItem(item = item, navController = navController)
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

