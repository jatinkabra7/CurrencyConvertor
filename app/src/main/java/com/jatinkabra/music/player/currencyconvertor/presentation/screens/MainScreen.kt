package com.jatinkabra.music.player.currencyconvertor.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jatinkabra.music.player.currencyconvertor.R
import com.jatinkabra.music.player.currencyconvertor.domain.model.Rate
import com.jatinkabra.music.player.currencyconvertor.ui.theme.Purple80
import com.jatinkabra.music.player.currencyconvertor.presentation.viewmodel.RateViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainScreen(modifier: Modifier = Modifier, vm : RateViewModel) {
    var input by remember {
        mutableStateOf("")
    }

    var response by remember {
        mutableStateOf("")
    }

    var fromTitle by remember {
        mutableStateOf("US Dollar")
    }

    var toTitle by remember {
        mutableStateOf("US Dollar")
    }

    var fromCurrency by remember {
        mutableStateOf("USD")
    }

    var toCurrency by remember {
        mutableStateOf("USD")
    }

    var fromRate by remember {
        mutableStateOf(1.0)
    }

    var toRate by remember {
        mutableStateOf(1.0)
    }
    
    var fromSelected by remember {
        mutableStateOf(false)
    }
    
    var toSelected by remember {
        mutableStateOf(false)
    }

    val rateList = vm.rateList.collectAsState().value

    Surface(
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background)
                .padding(20.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Currency Convertor",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = fromTitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start)
            )

            TextField(
                value = input,
                placeholder = { Text(text = "Enter Amount", fontSize = 30.sp)},
                onValueChange = {input = it},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    disabledPrefixColor = Color.Transparent,
                    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                singleLine = true,
                textStyle = TextStyle(fontSize = 30.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(30.dp))

            HorizontalDivider()

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = toTitle,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.Start)
            )

            TextField(
                value = if(input.isNotEmpty()) "%.2f".format((toRate/fromRate)*(input.toDouble())) else "",
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent,
                    disabledPrefixColor = Color.Transparent,
                    focusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedLabelColor = MaterialTheme.colorScheme.onBackground,
                    unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground.copy(0.7f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                singleLine = true,
                textStyle = TextStyle(fontSize = 30.sp),
                readOnly = true
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.inversePrimary.copy(0.4f),
                            RoundedCornerShape(20)
                        )
                        .height(50.dp)
                        .width(100.dp)
                        .clickable {
                            fromSelected = true
                        }
                ) {
                    Text(text = fromCurrency, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                }

                FloatingActionButton(
                    onClick = {

                        val t1 = fromRate
                        val t2 = fromTitle
                        val t3 = fromCurrency

                        fromRate = toRate
                        fromTitle = toTitle
                        fromCurrency = toCurrency

                        toRate = t1
                        toTitle = t2
                        toCurrency = t3
                    },
                    shape = CircleShape,
                    containerColor = Purple80.copy(0.4f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_sync_24),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.inversePrimary.copy(0.4f),
                            RoundedCornerShape(20)
                        )
                        .height(50.dp)
                        .width(100.dp)
                        .clickable {
                            toSelected = !toSelected
                        }
                ) {
                    Text(text = toCurrency, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
                }
            }

        }
        
        if(fromSelected) {
            FromBottomSheet(list = rateList, onDismiss = {fromSelected = false}) {
                fromTitle = it.currency
                fromCurrency = it.id
                fromRate = it.rate
            }
        }

        if(toSelected) {
            ToBottomSheet(list = rateList, onDismiss = {toSelected = false}) {
                toTitle = it.currency
                toCurrency = it.id
                toRate = it.rate
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FromBottomSheet(modifier: Modifier = Modifier, list: List<Rate>, onDismiss : () -> Unit, onCurrencySelection : (Rate) -> Unit) {
    val fromState = rememberModalBottomSheetState()
    
    ModalBottomSheet(
        sheetState = fromState,
        onDismissRequest = { onDismiss() },
        modifier = Modifier
    ) {
        LazyColumn {
            items(list) {

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCurrencySelection(it)
                            onDismiss()
                        }
                ) {

                    Spacer(modifier = Modifier.width(50.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.width(100.dp)
                    ) {

                        Text(
                            text = it.id,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = it.currency,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToBottomSheet(modifier: Modifier = Modifier, list : List<Rate>, onDismiss: () -> Unit, onCurrencySelection : (Rate) -> Unit) {
    val toState = rememberModalBottomSheetState()
    ModalBottomSheet(
        sheetState = toState,
        onDismissRequest = { onDismiss() },
        modifier = Modifier
    ) {
        LazyColumn {
            items(list) {
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onCurrencySelection(it)
                            onDismiss()
                        }
                ) {

                    Spacer(modifier = Modifier.width(50.dp))

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.width(100.dp)
                    ) {

                        Text(
                            text = it.id,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = it.currency,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                HorizontalDivider()
            }
        }
    }
}
