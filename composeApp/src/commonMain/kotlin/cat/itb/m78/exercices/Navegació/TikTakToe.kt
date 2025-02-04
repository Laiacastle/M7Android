package cat.itb.m78.exercices.Navegació

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cat.itb.m78.exercices.ViewModel.CountViewModel
import kotlinx.serialization.Serializable

object TikTak {
    @Serializable
    data object ScreenInici
    @Serializable
    data object ScreenPlay
    @Serializable
    data class ScreenWin(val message: String)
}
fun List<List<Boolean?>>.toMutableMatrix(): List<MutableList<Boolean?>> {
    return map { it.toMutableList() }
}
private class Play : ViewModel() {

    var llena = mutableStateOf(true)
    var equis = true
    var rodona = false
    var actual = mutableStateOf(equis)
    var list :List<List<Boolean?>> = (listOf(listOf(null, null, null), listOf(null, null, null), listOf(null, null, null)))
    var tablero = list.toMutableMatrix()
    var ficha = actual
    var ganador = mutableStateOf(tablero[0][0])

    fun addFicha( pos: Int, pos2: Int) {
        if (tablero[pos][pos2] == null) {
            tablero[pos][pos2] = ficha.value; actual.value = ficha.value
            changeFicha()
            comprovarArray()

        } else {
            actual.value = ficha.value
        }
    }

    fun changeFicha() {
        if (actual.value) {
            actual.value = rodona
        } else if (!actual.value) {
            actual.value = equis
        } else {
            actual.value = rodona
        }
    }
    fun Txt(): String {
        if(ficha.value){
            return "Turn of: Equis"
        }else{
            return "Turn of: Cercles"
        }
    }
    fun comprovarArray(){
        for(i in 0..2){
            for(j in 0..2){
                if (tablero[i][j] == null){
                    llena.value = false;
                }
            }
        }
    }
    fun Ganador(): Boolean{
        var ganar = mutableStateOf(false)
        var anterior = mutableStateOf(tablero[0][0])
        var anterior2 = anterior

            for(i in 0..2) {
                ganar.value = true
                for (j in 0..2) {
                    if (tablero[i][j] == anterior.value && anterior.value != null && ganar.value) {
                        ganar.value = true
                        anterior.value = tablero[i][j]
                    }else{ganar.value = false}
                    ganador.value = tablero[i][j]

                }
                if (ganar.value) {
                    return true
                }

            }
        for(i in 0..2) {
            ganar.value = true
            for (j in 0..2) {
                if (tablero[j][i] == anterior2.value && anterior2.value != null && ganar.value) {
                    ganar.value = true
                    anterior2.value = tablero[j][i]
                }else{ganar.value = false}
                ganador.value = tablero[j][i]
            }
            if (ganar.value) {
                return true
            }

        }

        if (tablero[0][0] == tablero[1][1] && tablero[2][2] == tablero[0][0] && tablero[0][0] != null){
            ganador.value = tablero[0][0]
            return true

        }

        if (tablero[0][2] == tablero[1][1] && tablero[2][0] == tablero[0][2] && tablero[0][2] != null){
            ganador.value = tablero[0][2]
            return true
        }

        return ganar.value

    }
}

@Composable
fun ScreenPlay(navigateToScreenWin: (String)-> Unit){
    Column(Modifier.background(Color.Red).fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        val viewModel = viewModel { Play() }
        var txt by remember{mutableStateOf('-')}

        val tablero = viewModel.tablero
        Text("TikTok")
        Text(viewModel.Txt())
        for(i in 0..2){
            Row {
                for (j in 0..2) {
                    if(tablero[i][j] == true){
                        txt = 'X'
                    }else if (tablero[i][j] == false){
                        txt = 'O'
                    }
                    else{
                        txt = '-'
                    }
                    var ganar by remember{mutableStateOf(false)}
                    Botones( viewModel::addFicha, txt ,i, j)
                    if(viewModel.Ganador()){
                        if(viewModel.ganador.value == true){
                            txt = 'X'
                        }else if (viewModel.ganador.value == false){
                            txt = 'O'
                        }else{
                            txt = '-'
                        }
                        ganar = true

                        //navigateToScreenWin("ganador:" + txt)
                    }
                    else if (viewModel.llena.value){
                        Text("empate")
                    }
                    if(ganar){
                        Text("ganador" + txt)
                    }
                }
            }
        }




        Button(onClick = { navigateToScreenWin("You Win!") }) {
            Text("Win")
        }
        Button(onClick = { navigateToScreenWin("You Lose!") }) {
            Text("Lose")
        }
    }
}
@Composable
fun Botones(onClick: (Int, Int) -> Unit, text: Char,pos: Int, pos2: Int){
    Button(onClick = {onClick(pos, pos2); }){
        Text(text.toString())
    }
}

@Composable
fun ScreenInici(navigateToScreenPlay: () -> Unit){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("Screen 1")
        Button(onClick = {navigateToScreenPlay()}){
            Text("Play")
        }
    }

}
@Composable
fun ScreenWin(message: String, navigateToScreenInici: ()-> Unit){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(message)
        Text("Screen 3")
        Button(onClick = {navigateToScreenInici()}){
            Text("Main Menu")
        }
    }
}

@Composable
fun TikTakScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = TikTak.ScreenInici) {

        composable<TikTak.ScreenInici> {
            ScreenInici{navController.navigate(TikTak.ScreenPlay)}
        }
        composable<TikTak.ScreenPlay> {
            ScreenPlay { navController.navigate(TikTak.ScreenWin(it)) }
        }
        composable<TikTak.ScreenWin> { backStack ->
            val message = backStack.toRoute<TikTak.ScreenWin>().message
            ScreenWin(message){ navController.navigate(TikTak.ScreenInici) }
        }
    }
}


