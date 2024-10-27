package com.myprojects.aionandroid.text_to_image

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.formData
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.util.Base64


//// Function to generate image from text and return Base64 result
//suspend fun generateImageOfText(text: String, aspectRatio: String = "1:1", apiKey: String): Result<String> {
//    val httpClient = HttpClient(CIO) {
//        install(io.ktor.client.features.json.JsonFeature) {
//            serializer = io.ktor.client.serialization.kotlinx.json.KotlinxSerializer()
//        }
//    }
//    return try {
//        val response = httpClient.post("https://api.stability.ai/v2beta/stable-image/generate/core") {
//            header("authorization", apiKey)
//            header("accept", "application/json")
//            setBody(MultiPartFormDataContent(
//                formData {
//                    append("\"prompt\"", text, Headers.build {
//                        append(HttpHeaders.ContentDisposition, "form-data; name=\"prompt\"")
//                    })
//                }
//            ))
//        }
//
//        // Assuming the API returns a Base64 string in the response
//        val result: Base64Result = response.body()
//        Result.Success(result.data) // Adjust this line based on the actual structure of the response
//    } catch (e: Exception) {
//        e.printStackTrace()
//        Log.e("ImageGenerator", "Error generating image: ${e.localizedMessage}")
//        Result.Error(e.message ?: "Unknown error")
//    } finally {
//        httpClient.close()
//    }
//}

suspend fun generateImageOfText(
    text: String,
    aspectRatio: String = "1:1",
    apiKey: String
): Result {
    val httpClient = HttpClient(CIO)
    return try {
        val response = httpClient.post("https://api.stability.ai/v2beta/stable-image/generate/core") {
            header("authorization", apiKey)
            header("accept", "image/*")
//            setBody(MultiPartFormDataContent(
////                formData {
////                    append("prompt", text)
////                    append("aspect_ratio", aspectRatio)
////                }
////            ))
//        }
//        val result: Base64Result = response.body()
//        Result.Success(result.base64Image)
            setBody(MultiPartFormDataContent(
                formData {
                    append("\"prompt\"", text, Headers.build {
                        append(HttpHeaders.ContentDisposition, "form-data; name=\"prompt\"")
                    })
                },
                boundary = "WebAppBoundary",
            ))
        }
        Log.d("ImageGenerator", "Response status: ${response.status}")
        val byteArray = response.body<ByteArray>()
        val base64Image = Base64.getEncoder().encodeToString(byteArray)
        Result.Success(base64Image)
    } catch (e: Exception) {
        e.printStackTrace()
        Result.Error(e.message ?: "Unknown error")
    } finally {
        httpClient.close()
    }
}