package com.joel.car_compose.model

/*
"id": 1,
"name": "BMW 320i",
"overview": "The 2015 BMW 3 Series is a good luxury small car with above-average performance chops. It’s available as a sedan, Sports Wagon, and Gran Turismo hatchback, and it boasts a multitude of engine choices, good fuel economy, an abundance of power, and swift acceleration from a stop.\r\n\r\nWhile the 3 Series’ sharp driving dynamics and cushioned ride are admirable, some may long for better steering feedback when tackling sharp corners. Additionally, several cars in the class offer a nicer interior and better reliability ratings.",
"horsepower": "2000cc",
"price": 2000000.0,
"image": "https://joel14290.pythonanywhere.com/media/images.jpg",
"model": "BMW 3 series",
"fuel": "Petrol",
"quantity": 2,
"color": "White",
"transmission": 1,
"year_of_manufacture": 2015,
"brand": 2
*/
data class Car(
    val id: Int,
    val name: String,
    val overView : String,
    val horsepower : String,
    val price : Double,
    val image : String,
    val model : String,
    val fuel : String,
    val quantity : Int,
    val color: String,
    val transmission: Int,
    val year_of_manufacture : Int,
    val brand : Int,
)
