package com.joel.car_compose.ui.cars

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R
import com.joel.car_compose.components.BrandCardItem
import com.joel.car_compose.components.CarCardItem
import com.joel.car_compose.model.data.BrandItem
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.ui.destinations.ProfileScreenDestination
import com.joel.car_compose.viewmodel.CarHomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ListScreen(
    navigator: DestinationsNavigator,
    ){

    val carHomeViewModel = CarHomeViewModel()
    ListScreenTools(navigator , carHomeViewModel )

}

@Composable
fun ListScreenTools(
    navigator: DestinationsNavigator,
    carHomeViewModel: CarHomeViewModel,

    ){

    Surface(
        modifier = Modifier
            .padding(all = 10.dp)

    ) {
        Column{
            NavigationBar(navigator)
            Spacer(modifier = Modifier.height(5.dp))

            Text(text = "Brands")
            Spacer(modifier = Modifier.height(5.dp))

            BrandList(
                brandList = carHomeViewModel.brandListResponse,
            )
            carHomeViewModel.getBrandData()

            Spacer(modifier = Modifier.height(5.dp))
            CarList(
                carList = carHomeViewModel.carListResponse,
                navigator,
            )
            carHomeViewModel.getCarData()
        }
    }
}


@Composable
fun CarList(
    carList : List<CarItem>,
    navigator: DestinationsNavigator,

    ){

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(25.dp),
    )
    {
        item {


        }
        items(
            items = carList,
            itemContent = {
                CarCardItem(
                    car = it,
                    navigator
                )
            }
        )
    }
}

@Composable
fun BrandList(
    brandList: List<BrandItem>,
){
    LazyRow(
        modifier = Modifier
            .padding(all = 2.dp),

    ){
        itemsIndexed(
            items = brandList){ _, brand->
            BrandCardItem(
                brand = brand,
            )
        }
    }
}

@Composable
fun NavigationBar(
    navigator: DestinationsNavigator,

){
    Row(

       verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(

            modifier = Modifier
                .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp))
                .size(60.dp),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = {
                    navigator.navigate(ProfileScreenDestination)
                },

                ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_person_24),
                    contentDescription = "person",
                    modifier = Modifier
                        .height(55.dp)
                        .width(55.dp),
                    tint = Color.Black
                )
            }
        }
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Hello Joel",
                style = MaterialTheme.typography.h4

            )
            Text(
                text = "Welcome Back to Auto Cars",
                style = MaterialTheme.typography.h6
            )
        }
    }
}




