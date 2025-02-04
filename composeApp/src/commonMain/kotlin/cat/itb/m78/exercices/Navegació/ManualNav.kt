package cat.itb.m78.exercices.Navegació

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

private class ManualNavAppViewModel : ViewModel() {
    val currentScreen = mutableStateOf<Screen>(Screen.ScreenDefault)
    fun navigateTo(screen: Screen) {
        currentScreen.value = screen
    }
}

private sealed interface Screen {
    data object ScreenDefault : Screen
    data object Screen1 : Screen
    data object Screen2 : Screen
    data class Screen3(val message: String) : Screen
}

@Composable
fun ScreenDefault(navigateToScreen1:() -> Unit, navigateToScreen2: ()-> Unit, navigateToScreen3: (String)-> Unit){
    Column{
        Button(onClick = navigateToScreen1){
            Text("Go to Screen1")
        }
        Button(onClick = navigateToScreen2){
            Text("Go To screen2")
        }

        Button(onClick = {navigateToScreen3("hello"); }){
            Text("Go to Screen3 Hello")
        }

        Button(onClick = {navigateToScreen3("bye");  }){
            Text("Go to Screen3 Bye")
        }

    }}

@Composable
fun Screen1(navigateToScreenDefault: () -> Unit){
    Column(Modifier.background(Color.Green).fillMaxSize(), horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.Bottom){
        Text("Screen 1")
        Button(onClick = {navigateToScreenDefault()}){
            Text("Main menu")
        }
    }

}

@Composable
fun Screen2(navigateToScreenDefault: ()-> Unit){
    Column(Modifier.background(Color.Red).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Screen 2")
        Button(onClick = { navigateToScreenDefault() }) {
            Text("Main Menu")
        }
    }
}
@Composable
fun Screen3(message: String, navigateToScreenDefault: ()-> Unit){
    Column(Modifier.background(Color.Blue).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top){
    Text(message)
    Text("Screen 3")
    Button(onClick = {navigateToScreenDefault()}){
        Text("Main Menu")
    }
    }
}
@Composable
fun ManualNav() {
    val viewModel = viewModel { ManualNavAppViewModel() }
    val currentScreen = viewModel.currentScreen.value
    Column{
        when (currentScreen) {
            Screen.ScreenDefault -> ScreenDefault(
                navigateToScreen1 = {viewModel.navigateTo(Screen.Screen1)},
                navigateToScreen2 = {viewModel.navigateTo(Screen.Screen2) },
                navigateToScreen3 = {viewModel.navigateTo(Screen.Screen3(it))},
            )
            is Screen.Screen1 -> Screen1(navigateToScreenDefault = {viewModel.navigateTo(Screen.ScreenDefault)})
            is Screen.Screen2 -> Screen2(
                navigateToScreenDefault = { viewModel.navigateTo(Screen.ScreenDefault) }
            )
            is Screen.Screen3 -> Screen3(currentScreen.message, navigateToScreenDefault = {viewModel.navigateTo(Screen.ScreenDefault)})

        }
    }}

