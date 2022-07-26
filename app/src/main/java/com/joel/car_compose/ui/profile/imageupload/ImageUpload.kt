package com.joel.car_compose.ui.profile.imageupload

import androidx.compose.foundation.Image
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R

@Composable
fun ImageUpload(){
    Surface() {
        Card(
          //  border = BorderStroke(20.dp, Color.Cyan),
            contentColor = Color.Black,
            elevation = 10.dp,
            backgroundColor = Color.Cyan,

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_person_24),
                contentDescription = "Profile Photo",
                alignment = Alignment.CenterStart,

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageUploadPreview(){
    ImageUpload()
}