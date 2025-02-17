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
fun TrivialModel(navigateToScreenEnd: (Int) -> Unit){
    val model = viewModel { PlayTrivial() }
    ScreenTrivial(
        navigateToScreenEnd,
        model.time.value,
        model::minusTime,
        model::comprvCorrect,
        model::click,
        model.countEncertades.value,
        model.count.value,
        model.correct.value,
        model.rounds,
        model::resetTime,
        model.clicado.value,
        model::randomQuestion,
    )
}

@Composable
fun ScreenTrivial(navigateToScreenEnd: (Int) -> Unit, time:Int, minusTime:()->Unit, comprvCorrect:(String)->Unit, click:()->Unit, encertades: Int, rondesJugades: Int, correct: Boolean, rounds: Int, resetTime:()->Unit, clicado: Boolean, randomQuestion: () ->Unit){
    Column(Modifier.fillMaxSize().background(color = Colors.yellow), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        val model = viewModel { PlayTrivial() }
        CountDownScreen(time, minusTime)
        Text(currentQuestion.value.question, color = Color.Gray)
        Column(modifier = Modifier.padding(20.dp)){
            Row{
                Column{
                    for(i in 0..1){
                        buttons(comprvCorrect, model.answers[i], click)
                        Spacer(modifier = Modifier.height(20.dp).width(40.dp))
                    }

                }
                Spacer(modifier = Modifier.width(50.dp))
                Column{
                    for(i in 2..3){
                        buttons(comprvCorrect, model.answers[i], click)
                        Spacer(modifier = Modifier.height(20.dp).width(40.dp))
                    }
                }
            }

        }
        val points :Int = encertades
        if(rondesJugades < rounds){
            correct(clicado, correct, time, randomQuestion, resetTime)
            Text("Ronda: "+ rondesJugades, color = Color.Gray)
        }else{
            correct(clicado, correct, time)
            Text("Ronda "+ rondesJugades)
        }
        Button(onClick = {navigateToScreenEnd(points)}, colors = ButtonColors(Colors.pink, Colors.blue, Color.Black, Color.White)){
            Text("Exit")
        }
    }
}
