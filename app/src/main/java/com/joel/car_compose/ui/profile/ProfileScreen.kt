package com.joel.car_compose.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joel.car_compose.R
import com.joel.car_compose.ui.profile.imageupload.ImageUpload

// Should have 4 buttons ,i.e,
// image upload,
// Profile to edit user details,
// Wishlist (favourites),
// terms and policy or help
// Bids Made including invoices to be made

@Composable
fun ProfileScreen(){
    ProfileScreenButtons()
}

@Composable
fun ProfileScreenButtons(){
    Column {
        ImageUploadButton()
        Spacer(modifier = Modifier.height(30.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        UserDetailsButton()
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        MyWishListButton()
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        MyBids()
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        HelpCenter()
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        TermsAndPolicy()
        Spacer(modifier = Modifier.height(5.dp))
        Divider(
            color = Color.Black,
            thickness = 1.dp
        )
        LogOutButton()
    }
}


@Composable
fun ImageUploadButton(){
    Column(
        modifier = Modifier
            .height(100.dp)
            .width(100.dp)
            .fillMaxSize(3f)
            .clickable {  }
            .background(color = Color.Cyan),

    ) {
        ImageUpload()
    }


}

@Composable
fun UserDetailsButton(){
    Row(
        modifier = Modifier
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_person_24),
            contentDescription = "")

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "User Details")
        }


    }

}

@Composable
fun MyWishListButton(){
    Row(
        modifier = Modifier
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            contentDescription = "")

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Favorites")
        }


    }

}

@Composable
fun MyBids(){
    Row(
        modifier = Modifier
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_history_24),
            contentDescription = "")

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "My Bids")
        }


    }


}

@Composable
fun HelpCenter(){
    Row(
        modifier = Modifier
            .clickable {

            },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_help_24),
            contentDescription = "",
            modifier = Modifier

        )

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Help Center")
        }
    }
}

@Composable
fun TermsAndPolicy(){
    Row(
        modifier = Modifier
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_policy_24),
            contentDescription = "",

        )

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Terms and Policy")
        }

    }

}

@Composable
fun LogOutButton(){
    Row(
        modifier = Modifier
            .clickable {  },
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_logout_24),
            contentDescription = "")

        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Log Out")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreen()
}

