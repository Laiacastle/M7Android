package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import kotlinx.datetime.format.Padding

@Composable
fun Welcome(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Alignment.Center
        Text("Welcome!", fontSize = 2.em)
        Text("Start learning now")
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = {}){
            Text("Login")
        }
        Button(onClick = {}){
            Text("Register")
        }
    }
}