package com.joel.car_compose.ui.cars

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.joel.car_compose.R
import com.joel.car_compose.components.Favourite
import com.joel.car_compose.model.data.CarItem
import com.joel.car_compose.model.network.Resource
import com.joel.car_compose.theme.ButtonColor
import com.joel.car_compose.ui.destinations.ListScreenDestination
import com.joel.car_compose.viewmodel.CarDetailsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun CarDetailedScreen(
    navigator: DestinationsNavigator,
//    carId : Int,
    carInfo: CarItem,
    viewModel: CarDetailsViewModel = hiltViewModel(),
) {

//    val carInfoViewModel = produceState<Resource<CarItem>>(initialValue = Resource.Loading()){
//        value = viewModel.getCarInfo(carId)
//    }.value

   Column(
       modifier = Modifier
           .fillMaxSize()


   ) {
       Box(contentAlignment = Alignment.TopCenter) {
           DetailTopSection(
               navigator = navigator,
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.2f)
           )
       }

       Box(
           contentAlignment = Alignment.Center
       ) {
           CarDetails(carInfo = carInfo)
//
       }
       Box(contentAlignment = Alignment.BottomCenter) {
           BottomButtons(
               carInfo = carInfo,
               modifier = Modifier
                   .fillMaxWidth()
                   .fillMaxHeight(0.2f)
           )
       }
   }
}

@Composable
fun DetailTopSection(
    modifier: Modifier = Modifier,
    navigator: DestinationsNavigator,
//    carInfo: Resource<CarItem>
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .weight(1f)

        ) {
            IconButton(onClick = {
                navigator.navigate(ListScreenDestination)
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = "arrow back"
                )
            }
        }
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.weight(2f)
        ) {
            Text(
                text = "carInfo.model",
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        }

        Box(
            contentAlignment = Alignment.TopEnd,
            modifier = Modifier
                .weight(1f)
        ) {
            Favourite()
        }
    }

}

@Composable
fun DetailsWrapper(
    carInfo : Resource<CarItem>,
    modifier : Modifier = Modifier
){
    when(carInfo){
        is Resource.Success -> {
            CarDetails(carInfo = carInfo.data!!)

            
        }

        is Resource.Error -> {
           Box(contentAlignment = Alignment.Center) {
               Text(
                   text = carInfo.message!!,
                   color = Color.Red,
                   textAlign = TextAlign.Center,
                   style = MaterialTheme.typography.h4
               )
           }
        }
        is Resource.Loading -> {
            Box(
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .background(MaterialTheme.colors.background)
                        .scale(0.5f),
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}



@Composable
fun CarDetails(
    carInfo : CarItem
){
    val state = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state)
            .padding(12.dp)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = carInfo.name,
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.onSurface,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
            )
        }
        CarImage(carInfo = carInfo)
        Spacer(modifier = Modifier.height(8.dp))
        CarTechnicalDetails(carInfo = carInfo)
        Spacer(modifier = Modifier.height(8.dp))
        CarOtherDetails(carInfo = carInfo)
        Spacer(modifier = Modifier.height(8.dp))
        BottomButtons(carInfo = carInfo)
    }
}

@Composable
fun CarImage(
    carInfo: CarItem
){
    Box(
        contentAlignment = Alignment.Center,
    ) {
        SubcomposeAsyncImage(
            model = carInfo.image,
            contentDescription = carInfo.name,
            modifier = Modifier
                .fillMaxSize()
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(0.5f)
                )
            }
            else{
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
fun CarTechnicalDetails(
    carInfo : CarItem
) {

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState)
    ) {
        Card(
            elevation = 5.dp,
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.engine_icon),
                    contentDescription = "engine icon",
                    modifier = Modifier.size(60.dp)
                )
                Modifier.height(5.dp)
                Text(
                    text = carInfo.horsepower,
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Horsepower",
                    style = MaterialTheme.typography.h6,
                    color = Color.LightGray
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))
        Card(
            elevation = 5.dp,
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.fuel_icon),
                    contentDescription = "fuel icon",
                    modifier = Modifier.size(60.dp)
                )
                Modifier.height(5.dp)
                Text(
                    text = carInfo.fuel,
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Fuel",
                    style = MaterialTheme.typography.h6,
                    color = Color.LightGray,
                    overflow = TextOverflow.Visible
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))


        Card(
            elevation = 5.dp,
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.transmission_icon),
                    contentDescription = "Transmission icon",
                    modifier = Modifier.size(60.dp)
                )
                Modifier.height(5.dp)
                Text(
                    text = "${carInfo.transmission}",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Transmission ${carInfo.transmission}",
                    style = MaterialTheme.typography.h6,
                    color = Color.LightGray
                )
            }
        }
        Spacer(modifier = Modifier.width(20.dp))



        Card(
            elevation = 5.dp,
            modifier = Modifier.size(200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_access_time_24),
                    contentDescription = "time icon",
                    modifier = Modifier.size(60.dp)
                )
                Modifier.height(5.dp)
                Text(
                    text = "${carInfo.year_of_manufacture}",
                    style = MaterialTheme.typography.h5,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = "Year",
                    style = MaterialTheme.typography.h6,
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun CarOtherDetails(carInfo: CarItem){

    Column(
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "OverView",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text =carInfo.overview,
            style = MaterialTheme.typography.h6,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.ExtraBold
        )
    }


}

@Composable
fun BottomButtons(
    carInfo: CarItem,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(1.dp, color = Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    Text(text = "Price")
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "Ksh${carInfo.price}",
                        color = Color.LightGray,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(8.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.weight(1f)
        ) {
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = ButtonColor,
                    contentColor = MaterialTheme.colors.primary,
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Bid",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5
                )
            }
        }
    }
}


