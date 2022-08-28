package com.joel.car_compose.components

import android.text.Layout
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R

@Composable
fun MenuSearchArea(
    onSearchClicked: () -> Unit,
    onSortClicked: () -> Unit,
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(8.dp)
    ) {
        Sort (
            onSortClick = onSortClicked)
        Spacer(modifier = Modifier.width(150.dp))
        Search (onSearchClick = onSearchClicked, )
    }

}
@Composable
fun Sort(
    onSortClick : () -> Unit
){
    val sortIcon = painterResource(id = R.drawable.ic_baseline_sort_24)

    Button(onClick = {
                     TODO()
    },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.LightGray,
            contentColor = Color.Black
        ),
        shape = RoundedCornerShape(20),
        modifier = Modifier.size(35.dp)

    ) {
        Icon(painter = sortIcon, contentDescription = "sort")
    }

}

@Composable
fun Search(
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
){
    val searchIcon = painterResource(id = R.drawable.ic_baseline_search_24)
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.Black,
            backgroundColor = Color.LightGray
        ),
        shape = RoundedCornerShape(20),
        modifier = Modifier.size(35.dp)


    ) {
        Icon(painter = searchIcon, contentDescription = "search")

    }
}

@Composable
fun SearchAppBar(
    text : String,
    onTextChange : (String) -> Unit,
    onCloseClick : () -> Unit,
    onSearchClick : (String) -> Unit
){
    var text by remember {
        mutableStateOf("")
    }

    TextField(
        value = text,
        
        onValueChange = onTextChange,
        
        placeholder = {
            Text(
                text = "Search Cars,Brands....",
                color = Color.White,

            )
        },
        
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.White,
            cursorColor = Color.Black,
            textColor = Color.Black
        ),

        modifier = Modifier.height(56.dp),
        
        leadingIcon = {
            val searchIcon = painterResource(id = R.drawable.ic_baseline_search_24)
            Icon(painter = searchIcon, contentDescription = "search")
        },
        trailingIcon = {
           if(text.isNotBlank())
               IconButton(onClick = { text = "" }) {
                   Icon(
                       imageVector = Icons.Filled.Clear,
                       contentDescription = "clear"
                   )
               }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClick(text)
            }
        )
    )
}

@Preview(showBackground = true)
@Composable
fun MenuSearchAreaPreview(){
    MenuSearchArea(onSearchClicked = { /*TODO*/ }) {

    }
}


