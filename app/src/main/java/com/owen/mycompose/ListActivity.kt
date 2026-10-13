package com.owen.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owen.mycompose.ui.item.Greeting
import com.owen.mycompose.ui.theme.MyComposeTheme

class ListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                ListScreen()
            }
        }
    }

    @Composable
    private fun ListScreen() {
        Surface(
            modifier = Modifier.fillMaxSize().padding(2.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            GreetingsList()
        }
    }

    @Composable
    private fun GreetingsList(
        modifier: Modifier = Modifier,
        names: List<String> = List(100) { "$it" }
    ) {

        LazyColumn(
            modifier = modifier.padding(vertical = 4.dp)
        ) {
            items(items = names) { name ->
                Greeting(name = name)
            }
        }
    }

    @Preview(
        showBackground = true,
        widthDp = 320,
        uiMode = UI_MODE_NIGHT_YES,
        name = "DefaultPreviewDark"
    )
    @Preview(
        showBackground = true,
        widthDp = 320,
        name = "GreetingsPreview"
    )
    @Composable
    private fun GreetingsPreview() {
        MyComposeTheme() {
            ListScreen()
        }
    }
}






