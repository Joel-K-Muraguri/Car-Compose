//package com.joel.car_compose.ui.profile.wishlist
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Card
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import coil.compose.rememberAsyncImagePainter
//import com.ramcosta.composedestinations.navigation.DestinationsNavigator
//
//@OptIn(ExperimentalMaterialApi::class)
//@Composable
//fun FavouriteCarCard(
//    car: FavouriteResponseItem,
//    navigator: DestinationsNavigator
//
//){
//    Card(
//        elevation = 5.dp,
//        modifier = Modifier
//            .padding(10.dp),
//        onClick = {
//
//
//        }
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(8.dp)) {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//            ){
//                Image(
//                    painter = rememberAsyncImagePainter(model = "https://joel14290.pythonanywhere.com" + car.image),
//                    contentDescription = car.car_name,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(250.dp)
//                        .width(400.dp),
//
//                )
//            }
//            Text(text = car.car_name)
//        }
//    }
//}