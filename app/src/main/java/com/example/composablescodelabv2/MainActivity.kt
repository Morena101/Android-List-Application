package com.example.composablescodelabv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composablescodelabv2.ui.theme.ComposablesCodelabv2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposablesCodelabv2Theme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var showOnboarding by rememberSaveable { mutableStateOf(true) }

    if(showOnboarding){
        OnboardingScreen(onContinueClicked = {showOnboarding = false})
    }else{
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = List(100){"$it"}){
    // A surface container using the 'background' color from the theme
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
        
    ) {
        Column {
            LazyColumn{
                item { Text(text = "Greetings") }
                items(names){ name ->
                    Greeting(name)
                }
            }

        }
    }

}

@Composable
fun Greeting(name: String) {
    var expanded by remember{mutableStateOf(false)}
    val expandedPadding by animateDpAsState(
        targetValue = if (expanded)
                        48.dp
                      else
                          0.dp,
        label = "One of Wun",
        animationSpec = tween(durationMillis = 1500)
    )
    Surface(
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.padding(horizontal = 1.dp, vertical = 2.dp)

    ) {
        Row(Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = expandedPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }
            OutlinedButton(onClick = { expanded = !expanded}) {
                Text(
                    if (expanded)
                        "Show less"
                    else
                        "Show more"
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit
){
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Welcome to the Basics Codelab!"
            )
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text(text = "Continue")
            }

        }
    }

}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun OnboardingScreenPreview() {
    ComposablesCodelabv2Theme {
        OnboardingScreen(onContinueClicked = {})

    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposablesCodelabv2Theme {
        Greetings()

    }
}