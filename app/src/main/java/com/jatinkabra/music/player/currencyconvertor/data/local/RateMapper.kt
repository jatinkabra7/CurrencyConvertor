package com.jatinkabra.music.player.currencyconvertor.data.local

import com.jatinkabra.music.player.currencyconvertor.data.remote.RateModel
import com.jatinkabra.music.player.currencyconvertor.domain.model.Rate

fun RateEntity.toRate() : Rate {
    return Rate(
        id,currency,rate
    )
}

fun Rate.toRateEntity() : RateEntity {
    return RateEntity(
        id,currency, rate
    )
}

fun RateModel.toRateList() : List<Rate> {
    val data = this.data
    return listOf(
        Rate("AUD", "Australian Dollar", data.AUD),
        Rate("BGN", "Bulgarian Lev", data.BGN),
        Rate("BRL", "Brazilian Real", data.BRL),
        Rate("CAD", "Canadian Dollar", data.CAD),
        Rate("CHF", "Swiss Franc", data.CHF),
        Rate("CNY", "Chinese Yuan", data.CNY),
        Rate("CZK", "Czech Koruna", data.CZK),
        Rate("DKK", "Danish Krone", data.DKK),
        Rate("EUR", "Euro", data.EUR),
        Rate("GBP", "British Pound", data.GBP),
        Rate("HKD", "Hong Kong Dollar", data.HKD),
        Rate("HRK", "Croatian Kuna", data.HRK),
        Rate("HUF", "Hungarian Forint", data.HUF),
        Rate("IDR", "Indonesian Rupiah", data.IDR),
        Rate("ILS", "Israeli Shekel", data.ILS),
        Rate("INR", "Indian Rupee", data.INR),
        Rate("ISK", "Icelandic Krona", data.ISK),
        Rate("JPY", "Japanese Yen", data.JPY),
        Rate("KRW", "South Korean Won", data.KRW),
        Rate("MXN", "Mexican Peso", data.MXN),
        Rate("MYR", "Malaysian Ringgit", data.MYR),
        Rate("NOK", "Norwegian Krone", data.NOK),
        Rate("NZD", "New Zealand Dollar", data.NZD),
        Rate("PHP", "Philippine Peso", data.PHP),
        Rate("PLN", "Polish Zloty", data.PLN),
        Rate("RON", "Romanian Leu", data.RON),
        Rate("RUB", "Russian Ruble", data.RUB),
        Rate("SEK", "Swedish Krona", data.SEK),
        Rate("SGD", "Singapore Dollar", data.SGD),
        Rate("THB", "Thai Baht", data.THB),
        Rate("TRY", "Turkish Lira", data.TRY),
        Rate("USD", "US Dollar", data.USD.toDouble()),
        Rate("ZAR", "South African Rand", data.ZAR)
    )
}