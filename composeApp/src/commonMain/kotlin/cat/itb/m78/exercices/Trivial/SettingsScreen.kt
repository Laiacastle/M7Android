package cat.itb.m78.exercices.T5ivial
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*


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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember{mutableStateOf(TrivialSettingsManager.get().difficulty)}
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
                    })
            }

        }
    }}

@Composable
fun RadioButton(onClick: (Int)->Unit) {
    val radioOptions = listOf(5, 10, 15)
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(TrivialSettingsManager.get().rounds) }
    Row(modifier = Modifier.selectableGroup()) {
        radioOptions.forEach { text ->
                Row(
                    Modifier
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text); onClick(text)}
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