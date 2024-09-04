package com.meli.core.common.extensions

import java.text.NumberFormat
import java.util.Currency

fun Double?.toMoneyFormat(currency: String): String {
    val format = NumberFormat.getCurrencyInstance()
    format.setCurrency(Currency.getInstance(currency));
    return format.format(this)
}