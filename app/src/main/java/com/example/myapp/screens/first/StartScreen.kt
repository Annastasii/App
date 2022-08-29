package com.example.myapp.screens.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapp.R
import com.example.myapp.screens.Screen


@Composable
fun StartScreen(
    navController: NavController
) {
    Box() {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.light_green))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.img),
                contentDescription = "Higeia",
                modifier = Modifier
                    .padding(vertical = 100.dp)
            )
            Text(
                text = stringResource(R.string.covid),
                fontSize = 50.sp,
                modifier = Modifier
                    .padding(bottom = 15.dp)
            )
            Button(
                onClick = { navController.navigate(route = Screen.Country.route) },
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(20.dp)),
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = stringResource(R.string.list),
                    fontSize = 25.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .background(color = Color.White)
                )
            }
        }
    }

}

