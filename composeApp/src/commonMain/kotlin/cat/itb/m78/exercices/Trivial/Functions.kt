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

data class TrivialSettings(
    val difficulty : String ="Medium",
    val rounds: Int = 5,
    val time: Int = 10,
    val category: TrivialSubject = TrivialSubject.Random
)

data object TrivialSettingsManager{
    private var settings = TrivialSettings()
    fun update(newSettings: TrivialSettings){
        settings = newSettings
    }
    fun get() = settings
}

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
        Text("nextQuestion")
    }
}

@Composable
fun buttons(onClick: (String) ->Unit, message:String, click: ()-> Unit ){
    FilledTonalButton(onClick = {onClick(message); click()}, colors = ButtonColors(Colors.blue, Color.White, Color.Black, Color.White)){
        Text(message)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember{ mutableStateOf(TrivialSettingsManager.get().difficulty) }
    val difficult = listOf("Hard", "Easy", "Medium")
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier
    ) {
        OutlinedTextField(
            readOnly = true,
            value = selectedItem,
            onValueChange = {},
            label = { Text(text = "Dificulty") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .fillMaxWidth()
        )

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            difficult.forEach { option: String ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false
                        onClick(option)
                        selectedItem = option
                    }
                )
            }
        }
    }
}

@Composable
fun RadioButton(onClick: (Int)->Unit) {
    val radioOptions = listOf(5, 10, 15)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(TrivialSettingsManager.get().rounds) }
    Row(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                androidx.compose.material3.RadioButton(
                    selected = (text == selectedOption),
                    onClick = {onClick(text)}
                )
                Text(
                    text = text.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun SliderSettings(onClick: (Int) -> Unit) {
    var sliderPosition by remember { mutableStateOf(TrivialSettingsManager.get().time.toFloat()) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it;  onClick(it.toInt())},
            colors = SliderDefaults.colors(
                thumbColor = Colors.pink,
                activeTrackColor = Colors.green,
                inactiveTrackColor = Colors.blue,
            ),
            steps = 3,
            valueRange = 5f..20f
        )
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