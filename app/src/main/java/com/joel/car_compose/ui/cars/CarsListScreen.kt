package com.joel.car_compose.ui.cars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
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
    carHomeViewModel: CarHomeViewModel = viewModel(),

    ){

    ListScreenTools(navigator , carHomeViewModel )

}


@Composable
fun ListScreenTools(
    navigator: DestinationsNavigator,
    carHomeViewModel: CarHomeViewModel,
    ){

    Surface(
        color = Color.LightGray
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ){
            TopSection(navigator = navigator, carHomeViewModel = carHomeViewModel)
            Spacer(modifier = Modifier.height(5.dp))
            CarList(
                carList = carHomeViewModel.carListResponse,
                navigator,
            )
            LaunchedEffect(key1 = true) {
                //carHomeViewModel.getCarData()
            }
        }
    }
}

@Composable
fun TopSection(navigator: DestinationsNavigator, carHomeViewModel: CarHomeViewModel){
    val state = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(state)
    ) {
        NavigationBar(navigator)
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Brands",
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(5.dp))

        BrandList(
            brandList = carHomeViewModel.brandListResponse,
        )
        carHomeViewModel.getBrandData()

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Choose your Awesome Car",
            style = MaterialTheme.typography.h5,
        )
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
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Box(
          contentAlignment = Alignment.Center,
          modifier = Modifier
              .clip(RoundedCornerShape(20.dp))
              .background(color = Color.LightGray)

      ) {
          IconButton(onClick = {
              navigator.navigate(ProfileScreenDestination)
          }) {
              Icon(
                  painter = painterResource(id = R.drawable.ic_baseline_sort_24),
                  contentDescription = "sort",

              )
          }
      }
        Spacer(modifier = Modifier.width(160.dp))

        Box(
            contentAlignment = Alignment.TopEnd,
        ) {
            User()
        }
    }
}

@Composable
fun User(){

        Button(
            onClick = {


            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(8.dp),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(text = "Joel")
            Spacer(modifier = Modifier.padding(2.dp))
            Image(
                painter = painterResource(id = R.drawable.img_user),
                contentDescription = "user image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )
        }

}


@Preview
@Composable
fun UserPreview(){
    User()
}



