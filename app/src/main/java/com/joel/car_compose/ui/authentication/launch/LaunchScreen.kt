package com.joel.car_compose.ui.authentication.launch

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joel.car_compose.R
import com.joel.car_compose.ui.theme.BlueButton
import com.joel.car_compose.ui.theme.White
import com.joel.car_compose.ui.theme.Yellowish
import com.joel.car_compose.utils.Routes

@Composable
fun LaunchScreen(
    navController: NavHostController
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
        PageContentSection(navController)
    }
}

@Composable
fun PageContentSection(
    navController: NavHostController
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
                       onClick = { navController.navigate(Routes.AUTHENTICATION_SCREEN) },
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




@Preview(showBackground = true)
@Composable
fun LaunchScreenPreview(){
    val navController = rememberNavController()
    LaunchScreen(navController)
}