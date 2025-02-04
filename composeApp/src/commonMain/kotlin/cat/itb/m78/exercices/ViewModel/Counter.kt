package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class CountViewModel () : ViewModel(){
    var score = mutableStateOf(0)
    var score2 = mutableStateOf(0)
    fun upScore() { score.value++}
    fun upScore2() { score2.value++}
}
@Composable
fun count(){
    val viewModel = viewModel { CountViewModel() }
    count(viewModel.score.value, viewModel.score2.value ,viewModel::upScore, viewModel::upScore2)
}
@Composable
fun count(score : Int, score2 : Int, onClick: ()-> Unit, onClick2: ()-> Unit ){
        Row{
            button(score, onClick)
            button(score2, onClick2)
        }
}

@Composable
fun button(score: Int, onClick: () -> Unit){
    Column{
        Text(score.toString())
        Button(onClick = onClick){
            Text("Score")
        }
    }

}