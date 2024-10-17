package com.myprojects.aionandroid.text_to_image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenerateImageScreen(viewModel: ImageGenerationViewModel) {
    var imageBitmap by remember { mutableStateOf<Bitmap?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            // Define params for the image generation request
            val params = mapOf("param1" to "value1", "param2" to "value2")
            val apiKey = "sk-01go70PzduiRpHmkkW0i5f1l0E4snQlO4HhFt8bWJ3A96tq2"

            viewModel.generateImage(apiKey, null, params) { result ->
                result.onSuccess { responseBody ->
                    val inputStream = responseBody.byteStream()
                    imageBitmap = BitmapFactory.decodeStream(inputStream)
                }.onFailure {
                    // Handle failure
                }
            }
        }) {
            Text("Generate Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        GeneratedImageView(imageBitmap)
    }
}
