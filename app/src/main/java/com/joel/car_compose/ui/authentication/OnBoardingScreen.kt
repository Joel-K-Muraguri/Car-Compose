package com.joel.car_compose.ui.authentication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R
import com.joel.car_compose.theme.BlueButton
import com.joel.car_compose.theme.White
import com.joel.car_compose.theme.Yellowish
import com.joel.car_compose.ui.destinations.AuthenticationScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun LaunchScreen(
   navigator: DestinationsNavigator
){
    Image(
        painter = painterResource(id = R.drawable.img_ferrari),
        contentDescription = "background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PageContentSection(navigator)
    }
}

@Composable
fun PageContentSection(
    navigator: DestinationsNavigator
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .offset(y = 170.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
              Box(
                  contentAlignment = Alignment.CenterStart
              ) {
                  Text(
                      text = "Get Started with Auto Cars",
                      style = MaterialTheme.typography.h4,
                      fontWeight = FontWeight.Bold,
                      color = Yellowish
                  )
              }
            
            Spacer(modifier = Modifier.height(300.dp))

           
           Box(
               contentAlignment = Alignment.BottomStart
           ) {
               Row (
                   verticalAlignment = Alignment.CenterVertically,
                   horizontalArrangement = Arrangement.End
               ) {
                   Text(
                       text = "Skip",
                       style = MaterialTheme.typography.body1,
                       color = Color.White,
                       fontWeight = FontWeight.Bold

                   )
                   Spacer(modifier = Modifier.padding(50.dp))

                   Button(
                       onClick = {
                                 navigator.navigate(AuthenticationScreenDestination)
                       },
                       colors = ButtonDefaults.buttonColors(
                           contentColor = Color.White,
                           backgroundColor = BlueButton
                       ),
                       modifier = Modifier
                           .clip(RoundedCornerShape(30.dp))
                           .height(60.dp)

                   ) {
                       Icon(
                           imageVector = Icons.Default.ArrowForward,
                           contentDescription = "",
                           modifier = Modifier
                               .background(Color.White.copy(0.1f))
                               .clip(RoundedCornerShape(50.dp))
                               .padding(5.dp)
                               .size(35.dp)
                       )
                       Spacer(modifier = Modifier.padding(5.dp))

                       Text(
                           text = "Get Started",
                           color = White
                       )
                   }
               }
           }
        }
    }
}



