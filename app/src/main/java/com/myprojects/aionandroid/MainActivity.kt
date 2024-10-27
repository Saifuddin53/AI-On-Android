package com.myprojects.aionandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.myprojects.aionandroid.text_to_image.GenerateImageScreen
import com.myprojects.aionandroid.ui.theme.AIOnAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIOnAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    GenerateImageScreen(apiKey = "sk-ZZo7clciMLCWsV61jjbmlNRkzigOh5ZHFEvgeTgE5CHH04z7")
                }
            }
        }
    }
}