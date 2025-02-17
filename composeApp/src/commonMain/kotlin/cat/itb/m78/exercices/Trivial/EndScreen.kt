package cat.itb.m78.exercices.T5ivial
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ScreenEnd(navigateToScreenInici: ()-> Unit, points: Int){
    val model = viewModel { PlayTrivial() }
    Column(Modifier.fillMaxSize().background(color = Colors.yellow), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

        Text("Puntuació: "+points+"/"+model.rounds, color = Colors.green)
        Button(onClick = {navigateToScreenInici()}, colors = ButtonColors(Colors.pink, Colors.blue, Color.Black, Color.White)) {
            Text("Main menu")
        }
    }
}