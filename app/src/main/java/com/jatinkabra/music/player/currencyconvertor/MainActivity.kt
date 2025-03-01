package com.jatinkabra.music.player.currencyconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jatinkabra.music.player.currencyconvertor.presentation.screens.MainScreen
import com.jatinkabra.music.player.currencyconvertor.ui.theme.CurrencyConvertorTheme
import com.jatinkabra.music.player.currencyconvertor.presentation.viewmodel.RateViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConvertorTheme {

                val vm : RateViewModel = hiltViewModel()
                MainScreen(vm = vm)
            }
        }
    }
}