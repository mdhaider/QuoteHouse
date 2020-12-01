package com.nehal.quotehouse.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nehal.quotehouse.R

@Composable
fun QuotesThemeSwitch(onToggle: () -> Unit) {
    val icon = vectorResource(R.drawable.ic_day)
    Icon(icon, Modifier.padding(end = 8.dp).clickable(onClick = onToggle))
}
