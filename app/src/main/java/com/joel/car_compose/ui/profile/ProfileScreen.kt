package com.joel.car_compose.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    }
}


@Composable
fun ImageUploadButton(){
    ImageUpload()

}

@Composable
fun UserDetailsButton(){

}

@Composable
fun MyWishListButton(){

}

@Composable
fun MyBids(){


}

@Composable
fun HelpCenter(){


}

@Composable
fun TermsAndPolicy(){

}

