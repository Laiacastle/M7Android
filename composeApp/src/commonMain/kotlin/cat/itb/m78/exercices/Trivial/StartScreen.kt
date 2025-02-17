package cat.itb.m78.exercices.T5ivial
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.GatoInteligente
import m78exercices.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ScreenInici(navigateToScreenCategory: () -> Unit, navigateToScreenSettings: () -> Unit){

    Column(Modifier.fillMaxSize().background(color = Colors.yellow), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){

        Image(painter = painterResource(Res.drawable.GatoInteligente), contentDescription = null, modifier = Modifier.size(200.dp).border(2.dp, Color.White, CircleShape).clip(
            CircleShape
        ))
        Text("TRIVIAL", fontSize = 4.em, fontWeight = FontWeight.Bold, color = Colors.green, /*fontFamily = FontFamily(Font(Res.font.MilkyNice))*/)
        ElevatedButton(onClick = {navigateToScreenCategory()}, colors = ButtonColors(Colors.blue, Colors.pink, Color.Black, Color.White)){
            Text("Play")
        }
        ElevatedButton(onClick = {navigateToScreenSettings()}, colors = ButtonColors(Colors.pink, Colors.blue, Color.Black, Color.White)){
            Text("Settings")
        }
    }

}