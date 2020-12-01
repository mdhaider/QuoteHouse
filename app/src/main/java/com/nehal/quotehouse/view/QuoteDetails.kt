package com.nehal.quotehouse.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.nehal.quotehouse.components.DetailCard
import com.nehal.quotehouse.ui.JetQuotesTheme
import com.nehal.quotehouse.R


class QuoteDetails : AppCompatActivity() {
    private var quote: String? = null
    private var author: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // receive bundles here
        quote = intent.getStringExtra("quote") ?: "No values for quote"
        author = intent.getStringExtra("author") ?: "No values for author"

        setContent {
            JetQuotesTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DetailQuoteApp(quote = quote!!, author = author!!) {
                        onBackPressed()
                    }
                }
            }
        }
    }
}

@Composable
fun DetailQuoteApp(quote: String, author: String, onback: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "JetQuotes",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            navigationIcon = {
                IconButton(onClick = onback) {
                    Icon(asset = vectorResource(id = R.drawable.ic_back))
                }
            },
            elevation = 0.dp
        )
    }, bodyContent = {
        // pass quote & author params to details card
        DetailCard(
            quote = quote,
            author = author
        )

    })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetQuotesTheme {
        DetailCard(quote = "All is well", author = "Sanju")
    }
}