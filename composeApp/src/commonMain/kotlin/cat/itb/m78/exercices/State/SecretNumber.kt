package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlin.random.Random

@Composable
fun SecretNumber(){
    val textIntents ="intents: "
    val textIntro = "Endivina el numero secret"
    val numSecret = remember {(1..100).random()}
    var text by remember {mutableStateOf("")}
    var intents by remember {mutableStateOf(0)}
    var numVal by remember {mutableStateOf("")}
    Column{
        Text(textIntro)
        TextField(text,
            label = { },
            onValueChange = {
                text = it
            })
        Button(onClick = {
            if(text == ""){text = "0"}
            intents ++
            numVal = text
            text = ""

        }) {
            Text("Validar")
        }
        Text(textIntents + intents)
        val message = if(numVal.toInt() == 0){
            "Escriu un numero"
        }
        else if(numVal.toInt() > numSecret){
            "El numero es massa gran"
        }
        else if (numVal.toInt() < numSecret){
            "El numero es massa petit"
        }
        else{
            "Has encertat!"
        }
        Text(message)


    }
}