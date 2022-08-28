package com.joel.car_compose.ui.profile.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.joel.car_compose.model.fav.FavouriteResponseItem
import com.joel.car_compose.ui.cars.CarSharedViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavouriteScreen(

    navigator: DestinationsNavigator,
){


    val context = LocalContext.current
    val carSharedViewModel = CarSharedViewModel(context)
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        FavouriteScreenTools(carList = carSharedViewModel.favouriteListResponse, navigator )
                carSharedViewModel.getFavourites(context)

    }

}


@Composable
fun FavouriteScreenTools(
    carList : List<FavouriteResponseItem>,
    navigator: DestinationsNavigator,

){
    LazyColumn{
      items(
          items = carList,
          itemContent = {
              FavouriteCarCard(car = it,navigator)
          }
      )
    }
}

