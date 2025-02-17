package cat.itb.m78.exercices.T5ivial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds



data object Colors{
    val yellow = Color(0xfffbe7c6)
    val green = Color(0xffb4f8c8)
    val blue = Color(0xffa0e7e5)
    val pink = Color(0xffffaebc)
}

@Composable
fun correct(click: Boolean, correct: Boolean, time: Int){
    if(time > 0){
        if(click){
            if(!correct){
                Text("INCORRECT!")
            }else{
                Text("CORRECT!")

            }
            Text("Ja no hi ha més rondas!")
        }
    }else{
        Text("S'ha acabat el temps!")
    }

}

@Composable
fun correct(click: Boolean, correct: Boolean, time: Int, question: () -> Unit, resetTime: () -> Unit){
    if(time >0){
        if(click){
            if(!correct){
                Text("INCORRECT!")
            }else{
                Text("CORRECT!")

            }
            nextQuestion(question, resetTime)
        }
    }else{
        Text("S'ha acabat el temps!")
        nextQuestion(question, resetTime)
    }

}

@Composable
fun nextQuestion(onClick : () ->Unit, resetTime: ()-> Unit){
    OutlinedButton(onClick = {onClick(); resetTime()}){
        Text("Next question")
    }
}

@Composable
fun buttons(onClick: (String) ->Unit, message:String, click: ()-> Unit ){
    FilledTonalButton(onClick = {onClick(message); click()}, colors = ButtonColors(Colors.blue, Color.White, Color.Black, Color.White)){
        Text(message)
    }
}



@Composable
fun CountDownScreen(time: Int, minusTime: () -> Unit){
    LaunchedEffect(time){
        delay(1.seconds)

        if(time>0){
            minusTime()
        }
    }
    Column{
        Text(time.toString(), color = Colors.blue)
    }
}