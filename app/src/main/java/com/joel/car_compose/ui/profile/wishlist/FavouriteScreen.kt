package com.joel.car_compose.ui.profile.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.joel.car_compose.model.fav.FavouriteResponseItem
import com.joel.car_compose.ui.cars.CarHomeViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun FavouriteScreen(

    navigator: DestinationsNavigator,
){


    val context = LocalContext.current
    val carHomeViewModel = CarHomeViewModel()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        FavouriteScreenTools(carList = carHomeViewModel.favouriteListResponse, navigator )
                carHomeViewModel.getFavourites(context)

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

