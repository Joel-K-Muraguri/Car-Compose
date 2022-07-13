package com.joel.car_compose.ui.cars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.model.DataStore

@Composable
fun ListScreen(navController: NavHostController){
    Surface {
        ListScreenTools(navController)
    }
}

@Composable
fun ListScreenTools(navController: NavHostController){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(all = 10.dp)

    ) {
        SearchAppBar()
        CarList(navController )
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
            .padding(all = 10.dp),
    ) {
        TextField(
            value = text ,
            onValueChange = {text = it},
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search")
            },
            textStyle = TextStyle(color = Color.Black)

        )
    }

}

@Composable
fun CarList(
    navController: NavHostController
){

    val cars = remember {
        DataStore.carList
    }

    LazyColumn{
        items(
            items = cars,
            itemContent = {
                CarCardItem(car = it)
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    val navController = rememberNavController()
    ListScreen(navController)
}