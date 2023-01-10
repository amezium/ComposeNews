package com.azurgames.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.azurgames.vknewsclient.MainScreen
import com.azurgames.vknewsclient.ui.theme.NewsClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsClientTheme {
                MainScreen()
            }
        }
    }
}




