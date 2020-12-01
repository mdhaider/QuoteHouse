package com.nehal.quotehouse.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ContextAmbient
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import com.nehal.quotehouse.components.QuotesList
import com.nehal.quotehouse.components.QuotesThemeSwitch
import com.nehal.quotehouse.data.preference.PrefsManager
import com.nehal.quotehouse.data.preference.UiMode
import com.nehal.quotehouse.model.Quote
import com.nehal.quotehouse.ui.JetQuotesTheme
import com.nehal.quotehouse.viewModel.PostViewModel


class QuotesActivity : AppCompatActivity() {
    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefsManager = PrefsManager(context = this)
        lifecycleScope.launch {
            prefsManager.uiModeFlow.collect {
                when (it) {
                    UiMode.DARK -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    UiMode.LIGHT -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }

        setContent {
            val currentTheme = isSystemInDarkTheme()
            val darkMode by prefsManager.uiModeFlow.map { uiMode ->
                when (uiMode) {
                    UiMode.DARK -> {
                        true
                    }
                    UiMode.LIGHT -> {
                        false
                    }
                }
            }.collectAsState(initial = currentTheme)

            val toggleTheme: () -> Unit = {
                lifecycleScope.launch {
                    prefsManager.setUiMode(if (darkMode) UiMode.LIGHT else UiMode.DARK)
                }
            }

            JetQuotesTheme(darkMode) {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    App(toggleTheme)
                }
            }
        }
    }

    companion object {
        fun launchQuoteDetails(context: Context?, quote: String, author: String) {
            val intent = Intent(context, QuoteDetails::class.java).apply {
                putExtra("quote", quote)
                putExtra("author", author)
            }
            context?.startActivity(intent)
        }
    }
}


@Composable
fun getQuotes(): List<Quote>? {
    val context = ContextAmbient.current
     lateinit var postViewModel: PostViewModel
     lateinit var recyclerView: RecyclerView
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val listType = Types.newParameterizedType(List::class.java, Quote::class.java)
    val adapter: JsonAdapter<List<Quote>> = moshi.adapter(listType)
    val myJson = context.assets.open("quotes.json").bufferedReader().use { it.readText() }


    val data: MutableMap<String, String> = HashMap()
    data["count"] = "1"
    data["limit"] = "30"
    data["order"] = "quoteText"

   /* postViewModel.getPost(data)
    postViewModel.postData.observe(requireActivity(), Observer {
        Log.d(TAG, "onCreate: ${it[0].question}")
        postAdapter.setPostData(it as ArrayList<Mcq>)
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    })*/
    return adapter.fromJson(myJson)
}

@Composable
fun App(toggleTheme: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "QuoteHouse",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            elevation = 0.dp,
            actions = {
                QuotesThemeSwitch(toggleTheme)
            }
        )
    }, bodyContent = {
        // pass list of quotes
        getQuotes()?.let { quote -> QuotesList(quote) }
    })
}






