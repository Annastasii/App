package com.example.myapp.screens.country

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.model.db.CountryEntity
import com.example.myapp.screens.Screen

@Composable
fun CardItem(
    item : CountryEntity,
    navController: NavController
){
    Card(
        modifier = Modifier
            .width(330.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .padding(vertical = 5.dp),
        backgroundColor = colorResource(R.color.green)
    ) {
        Text(text = stringResource(id = R.string.country, item.country),
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