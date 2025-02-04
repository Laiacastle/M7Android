package cat.itb.m78.exercices.State

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun Good(){
    var text by remember { mutableStateOf("Good ?!")}
    Column{
        Text(text)
        Button(onClick = {
            text = "Good Morning!"
        }) {
            Text("Morning")
        }
        Button(onClick = {
            text = "Good Night!"
        }) {
            Text("Night")
        }


    }
}


