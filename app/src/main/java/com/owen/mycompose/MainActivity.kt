package com.owen.mycompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.owen.mycompose.list.ListActivity
import com.owen.mycompose.ui.item.Greeting
import com.owen.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    val context = LocalContext.current

    BackHandler(enabled = true) {
        if (!shouldShowOnboarding) {
            shouldShowOnboarding = true
        }
    }

    Surface(
        modifier = modifier.padding(2.dp),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // 上方頁面區域：給予 weight(1f) 讓他佔滿剩餘高度
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                if (shouldShowOnboarding) {
                    OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
                } else {
                    Greetings()
                }
            }

            // 底部按鈕：固定在畫面下方，開啟 ListActivity
            Button(
                onClick = {
                    val intent = Intent(context, ListActivity::class.java)
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
            ) {
                Text("Open List Page")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked() }
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyComposeTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("World", "Compose"),
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}



@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    MyComposeTheme {
        Greetings(modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    MyComposeTheme {
        MyApp(modifier = Modifier.fillMaxSize())
    }
}