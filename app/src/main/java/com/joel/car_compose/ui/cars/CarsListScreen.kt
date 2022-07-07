package com.joel.car_compose.ui.cars

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R

@Composable
fun ListScreen(){
    Surface {
        ListScreenTools()
    }
}

@Composable
fun ListScreenTools(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 10.dp)

    ) {
        ImageLogo()
        SearchAppBar()
    }
}

@Composable
fun SearchAppBar(){
    var text by remember {
        mutableStateOf("")
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 20.dp)
    ) {
        OutlinedTextField(
            value = text ,
            onValueChange = {text = it},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = CircleShape,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search")
            },

        )
    }

}

@Composable
fun CarList(){

}

@Composable
fun ImageLogo(){
    Card(
        modifier = Modifier
            .clip(CircleShape)
            ,
        elevation = 5.dp,
        backgroundColor = Color.Black
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "businnes logo" )
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    ListScreen()
}