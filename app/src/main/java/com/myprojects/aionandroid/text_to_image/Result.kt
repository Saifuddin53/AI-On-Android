package com.myprojects.aionandroid.text_to_image

import kotlinx.serialization.Serializable

sealed class Result {
    data class Success(val base64Image: String) : Result()
    data class Error(val message: String) : Result()
}



@Serializable
data class Base64Result(
    val base64Image: String
)