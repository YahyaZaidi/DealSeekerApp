package com.example.dealseekerapplication

data class CameraA(
    val id: Int,
    val name: String,
    val mainCategory: String,
    val subCategory: String,
    val image: String,
    val link: String,
    val ratings: Double,
    val noOfRatings: Int,
    val discountPrice: String,
    val actualPrice: String
)
