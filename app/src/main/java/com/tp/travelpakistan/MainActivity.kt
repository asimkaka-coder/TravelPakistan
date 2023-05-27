@file:OptIn(ExperimentalMaterial3Api::class)

package com.tp.travelpakistan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.tp.travelpakistan.ui.auth.ui.SignInScreen
import com.tp.travelpakistan.ui.home.HomeScreen
import com.tp.travelpakistan.ui.navigation.DestinationScreen
import com.tp.travelpakistan.ui.navigation.TravelNavigation
import com.tp.travelpakistan.ui.theme.TravelPakistanTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val themeState = rememberSaveable{
                mutableStateOf(false)
            }
            TravelPakistanTheme(
                darkTheme = themeState.value
            ) {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                TravelNavigation(navController = navController, startDestinationScreen =DestinationScreen.SignIn ){
                    themeState.value = !themeState.value
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TravelPakistanTheme {
        Greeting("Android")
    }
}