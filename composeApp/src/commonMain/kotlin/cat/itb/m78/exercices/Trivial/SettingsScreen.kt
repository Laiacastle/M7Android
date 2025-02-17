package cat.itb.m78.exercices.T5ivial
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ScreenSettings(navigateToScreenInici: () -> Unit){
    val model = viewModel{SettingsViewModel()}
    Column(Modifier.fillMaxSize().background(color = Colors.yellow).padding(50.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Row{
            Text("Difficulty")
            Spacer(modifier= Modifier.width((60.dp)))
            DynamicSelectTextField(model::updateDifficulty)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row {
            Text("rounds")
            Spacer(modifier = Modifier.width(60.dp))
            RadioButton(model::updateRounds)
        }
        Spacer(modifier = Modifier.height(40.dp))
        Row{
            Text("Time")
            Spacer(modifier = Modifier.width(60.dp))
            SliderSettings(model::updateTime)
        }
        Button(onClick = {navigateToScreenInici()}, colors = ButtonColors(Colors.pink, Colors.blue, Color.Black, Color.White)){
            Text("<--")
        }
    }
}