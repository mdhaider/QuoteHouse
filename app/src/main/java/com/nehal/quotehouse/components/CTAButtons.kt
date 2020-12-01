package com.nehal.quotehouse.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.nehal.quotehouse.utils.copyToClipboard
import com.nehal.quotehouse.utils.shareToOthers
import com.nehal.quotehouse.R

@Composable
fun CTAButtons(quote: String, author: String) {
    val context = ContextAmbient.current
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.background(MaterialTheme.colors.primaryVariant)
                .align(Alignment.BottomEnd)
                .padding(30.dp, 30.dp, 0.dp, 30.dp)
        ) {

            Button(
                icon = vectorResource(id = R.drawable.ic_copy),
                name = "COPY",
                modifier = Modifier.clickable(onClick = {
                    context.copyToClipboard(quote.plus("").plus("- $author"))
                    Toast.makeText(context, "Quote Copied!", Toast.LENGTH_SHORT).show()
                })
            )

            Spacer(modifier = Modifier.width(30.dp))

            Button(
                icon = vectorResource(id = R.drawable.ic_share),
                name = "SHARE",
                modifier = Modifier.clickable(onClick = {
                    context.shareToOthers(quote.plus("").plus("- $author"))
                })
            )

            Spacer(modifier = Modifier.width(30.dp))


        }
    }


}