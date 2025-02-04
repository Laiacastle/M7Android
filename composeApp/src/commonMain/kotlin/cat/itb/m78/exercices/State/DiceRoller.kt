package cat.itb.m78.exercices.State

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.dice_1
import m78exercices.composeapp.generated.resources.dice_2
import m78exercices.composeapp.generated.resources.dice_3
import m78exercices.composeapp.generated.resources.dice_4
import m78exercices.composeapp.generated.resources.dice_5
import m78exercices.composeapp.generated.resources.dice_6
import m78exercices.composeapp.generated.resources.tapestry
import m78exercices.composeapp.generated.resources.title
import org.jetbrains.compose.resources.painterResource


@Composable
fun diceRoller(){
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }){
        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(Res.drawable.tapestry), contentDescription = null, contentScale= ContentScale.FillBounds, modifier = Modifier.matchParentSize())
        }
        Column(modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            val dices = 1..6
            var dice1 by remember { mutableStateOf(dices.random())}
            var dice2 by remember { mutableStateOf(dices.random())}

            Image(painter = painterResource(Res.drawable.title), contentDescription = null,  modifier = Modifier.size(400.dp).padding(40.dp))
            Button(onClick = {
                dice1 = dices.random()
                dice2 = dices.random()
                if(dice1 == 6 && dice2 == 6){
                    scope.launch {
                        snackbarHostState.showSnackbar("JACKPOT!")
                    }
                }
            }){
                Text("Roll the dice")
            }
            Row{
                Dice(dice1)
                Dice(dice2)
            }

        }

    }
}

@Composable
fun Dice(value: Int){
    var image by remember { mutableStateOf(Res.drawable.dice_6)}
    when(value){
        1 -> image = Res.drawable.dice_1
        2 -> image = Res.drawable.dice_2
        3 -> image = Res.drawable.dice_3
        4 -> image = Res.drawable.dice_4
        5 -> image = Res.drawable.dice_5
        else -> image = Res.drawable.dice_6
    }
    Image(painter = painterResource(image), contentDescription = null)
}