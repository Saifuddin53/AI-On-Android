package com.myprojects.aionandroid.text_to_image

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap

@Composable
fun GeneratedImageView(imageBitmap: Bitmap?) {
    if (imageBitmap != null) {
        Image(
            bitmap = imageBitmap.asImageBitmap(),
            contentDescription = "Generated Image",
            modifier = Modifier.fillMaxWidth()
        )
    } else {
        Text("No image generated yet")
    }
}
