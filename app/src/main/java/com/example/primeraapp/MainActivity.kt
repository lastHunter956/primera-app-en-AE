package com.example.primeraapp
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
//import androidx.compose.material.icons.materialIcon
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.primeraapp.ui.theme.PrimeraAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrimeraAppTheme {
                // A surface container using the 'background' color from the theme
               MyApp()
            }
        }
    }
}
@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(botonContinuar = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}
@Composable
fun Greetings(names : List<String> = List(10) {"$it"}){
    Surface(color = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(vertical = 4.dp)){
            LazyColumn{
                item{ Text("barra de navegacion")}
                items(names){name->
                    Greeting(name)
                }

            }
        }
    }
}
@Composable
private fun Greeting(name: String) {
    val expandir = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState (
        targetValue = if (expandir.value) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column (
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = extraPadding)) {
                Text(text = "info: $name")
                Text(text = "espacio para subtitulo")
            }
            OutlinedButton(onClick = {expandir.value =!expandir.value}) {
                Text(if(expandir.value) "Mostrar menos" else "Mostrar mas")
            }
        }
    }
}
@Composable
fun OnboardingScreen(
    botonContinuar: ()-> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¿deseas entrar al programa?")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = botonContinuar
            ) {
                Text("Continuar")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    PrimeraAppTheme {
        OnboardingScreen(botonContinuar={})
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    PrimeraAppTheme {
        MyApp()
        //Greeting("Mundo")
    }
}
