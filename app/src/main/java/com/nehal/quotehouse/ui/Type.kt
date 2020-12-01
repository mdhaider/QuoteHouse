package com.nehal.quotehouse.ui


import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import com.nehal.quotehouse.R


private val futura = fontFamily(
        font(R.font.futurabook),
        font(R.font.futuramedium, FontWeight.W500),
        font(R.font.futurabold, FontWeight.Bold))


val typography = Typography(defaultFontFamily = futura)
