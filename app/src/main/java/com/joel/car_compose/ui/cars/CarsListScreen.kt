package com.joel.car_compose.ui.cars

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joel.car_compose.auth.SessionManager
import com.joel.car_compose.model.Car
import com.joel.car_compose.model.Brand

@Composable
fun ListScreen(
    navController: NavHostController,
    carSharedViewModel: CarSharedViewModel,
    context: Context
){
    Surface {
        ListScreenTools(
            navController,
            carSharedViewModel
        )
    }

    val token = SessionManager(context).fetchAuthToken()
    Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
}

@Composable
fun ListScreenTools(
    navController: NavHostController,
    carSharedViewModel: CarSharedViewModel
){

    Surface(
        modifier = Modifier
            .padding(all = 10.dp,)

    ) {
        Column() {
            SearchAppBar(
                hint = "Search car,brand..",
                modifier = Modifier.padding(16.dp)
            ){


            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(text = "Brands")
            Spacer(modifier = Modifier.height(5.dp))


            BrandList(
                brandList = carSharedViewModel.brandListResponse,
                carSharedViewModel
            )
            carSharedViewModel.getBrandData()


            Spacer(modifier = Modifier.height(10.dp))
            CarList(
                carList = carSharedViewModel.carListResponse,
                carSharedViewModel
            )
            carSharedViewModel.getCarData()

        }

    }
}

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    hint : String = "",
    onSearch : (String) -> Unit = {},
){
    var text by remember {
        mutableStateOf("")
    }
    var hintIsDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = Modifier){
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
//            modifier = Modifier
//                .fillMaxWidth()
//                .shadow(5.dp, RoundedCornerShape(10.dp))
//                .background(color = Color.White, RoundedCornerShape(10.dp))
//                .padding(horizontal = 20.dp, vertical = 12.dp)
//                .onFocusChanged {
//                    // hintIsDisplayed = it != FocusState.hasFocus
//                }
        )
        if (hintIsDisplayed){
            Text(
                text = text,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}

@Composable
fun CarList(
    carList : List<Car>,
    carSharedViewModel: CarSharedViewModel
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),

    ){
       itemsIndexed(
           items = carList){ _, auto->
           CarCardItem(
               car = auto,
               modifier = Modifier
                   .clickable {
                    //  carSharedViewModel.onEvents(ListScreenEvents.OnCarCardClick(auto))
                   }
           )
       }
    }

}

@Composable
fun BrandList(
    brandList : List<Brand>,
    carSharedViewModel: CarSharedViewModel

){
    LazyRow(
        modifier = Modifier
            .padding(all = 5.dp),


    ){
        itemsIndexed(
            items = brandList){ _, brand->
            BrandCardItem(
                brand = brand,
                modifier = Modifier.clickable {
                   // carSharedViewModel.onEvents(ListScreenEvents.OnBrandSortClick)
                }
            )
        }
    }
}


/* @Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    val navController = rememberNavController()
    ListScreen(navController)
} */