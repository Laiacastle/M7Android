package cat.itb.m78.exercices.State


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Hello(){
    var showDialog by remember { mutableStateOf(false) }
    var message by remember {mutableStateOf("Hello")}
    var text by remember { mutableStateOf("") }
    Column{
        TextField(text,
            label = { Text("Name") },
            onValueChange = {
                text = it
            })
        Button(onClick = {
            message += " " + text
            showDialog = true
        }) {
            Text("SayHello")
        }

        if(showDialog)
            AlertDialog(
                onDismissRequest={showDialog = false},
                confirmButton={},
                text = {Text(message)}
            )
    }

}


