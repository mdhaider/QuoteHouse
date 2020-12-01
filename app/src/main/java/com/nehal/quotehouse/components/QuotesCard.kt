package com.nehal.quotehouse.components

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.unit.dp
import com.nehal.quotehouse.model.Quote
import com.nehal.quotehouse.view.QuotesActivity.Companion.launchQuoteDetails

@Composable
fun QuotesCard(quote: Quote) {
    val context = ContextAmbient.current

    Column(modifier = Modifier.clickable(onClick = {
        launchQuoteDetails(context, quote.quote.toString(), quote.author.toString())

    }).background(MaterialTheme.colors.primaryVariant).padding(12.dp)) {

        Text(
            text = """ " """,
            style = typography.h4,
            color = MaterialTheme.colors.onBackground
        )

        Text(
            text = quote.quote.toString(),
            style = typography.body1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp)
        )

        Spacer(Modifier.preferredHeight(12.dp))

        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.CenterEnd).padding(12.dp),
                text = quote.author.toString().ifBlank { " - Unknown" },
                style = typography.caption,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(Modifier.preferredHeight(8.dp))
        }

    }
}
