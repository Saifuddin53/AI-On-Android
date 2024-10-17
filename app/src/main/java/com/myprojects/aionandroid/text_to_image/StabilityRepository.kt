package com.myprojects.aionandroid.text_to_image

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import java.io.File

class StabilityRepository {

    suspend fun generateImage(apiKey: String, imageFile: File?, params: Map<String, String>): Result<ResponseBody> {
        return withContext(Dispatchers.IO) {
            try {
                val imagePart = imageFile?.let {
                    val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), it)
                    MultipartBody.Part.createFormData("image", it.name, requestFile)
                }

                val response = RetrofitClient.instance.generateImage(
                    "Bearer $apiKey",
                    imagePart,
                    params
                )
                if (response.isSuccessful) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Failed to generate image: ${response.code()}"))
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error in generating image: ${e.localizedMessage}")
                Result.failure(e)
            }
        }
    }
}
