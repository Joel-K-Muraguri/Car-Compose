package com.joel.car_compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Favourite(){
    Surface(
        modifier =  Modifier.
                size(40.dp),
        shape = CircleShape,
        color = Color(0xDFF5FAFA)

    ) {
        FavouriteButton(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun FavouriteButton(
    modifier: Modifier = Modifier,
   // color: Colors = Color(0xffE91E63),
    color: Color = Color(0xFF0C0A0B)

){
    val isFavourite by remember {
        mutableStateOf(false)
    }

    IconToggleButton(
        checked = isFavourite,
        onCheckedChange = {
            isFavourite != isFavourite
        }
    ) {
        Icon(
            tint = color,
            modifier = Modifier,
            imageVector =
            if (isFavourite) Icons.Filled.Favorite
            else Icons.Default.FavoriteBorder,
            contentDescription = null
        )

    }

}

@Composable
fun FavouriteColor(){
    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null)
}

@Preview
@Composable
fun FavouriteButtonPreview(){
    Favourite()
}