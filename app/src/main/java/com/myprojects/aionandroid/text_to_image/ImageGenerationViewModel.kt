package com.myprojects.aionandroid.text_to_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File

class ImageGenerationViewModel : ViewModel() {
    private val repository = StabilityRepository()

    fun generateImage(apiKey: String, imageFile: File?, params: Map<String, String>, onResult: (Result<ResponseBody>) -> Unit) {
        viewModelScope.launch {
            val result = repository.generateImage(apiKey, imageFile, params)
            onResult(result)
        }
    }
}
