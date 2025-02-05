package cat.itb.m78.exercices.T5ivial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cat.itb.m78.exercices.Navegació.ScreenInici
import kotlinx.coroutines.delay
import kotlin.random.Random

object Trivial {
    @kotlinx.serialization.Serializable
    data object ScreenInici
    @kotlinx.serialization.Serializable
    data object ScreenTrivial
    @kotlinx.serialization.Serializable
    data object ScreenEnd
    @kotlinx.serialization.Serializable
    data object ScreenSettings
}
data class Question(val question: String, val correctAnswer: String, val incorrectAnswer: String, val incorrectAnswerTwo: String, val incorrectAnswerThree: String)
object MathematicQuestions{
    val question1 = Question("¿Cuál es el valor de pi (π) hasta tres decimales?", "3.14", "3.15", "3.12", "3.11")
    val question2 = Question("¿Cómo se llama el polígono con 12 lados?", "Dodecágono", "Decágono", "Heptágono", "Octágono")
    val question3 = Question("¿Qué es un número primo?", "Un número que solo tiene dos divisores: él mismo y 1", "Un número que tiene más de dos divisores", "Un número divisible por 3", "Un número mayor que 10")
    val question4 = Question("¿Cuántos grados tiene un ángulo recto?", "90°", "45°", "180°", "60°")
    val question5 = Question("Si sumamos los primeros 10 números naturales (1 + 2 + 3 + ... + 10), ¿cuál es el resultado?", "55", "45", "50", "60")
    val question6 = Question("¿Qué es el teorema de Pitágoras y qué fórmula lo representa?", "Relaciona los lados de un triángulo rectángulo: a² + b² = c²", "Relaciona los lados de un triángulo equilátero: a = b = c", "Relaciona el área de un círculo: A = πr²", "Relaciona las áreas de un cuadrado: A = l²")
    val question7 = Question("¿Cuál es la raíz cuadrada de 144?", "12", "14", "16", "18")
    val question8 = Question("¿Cómo se llama la figura geométrica que tiene todos sus lados de igual longitud y todos sus ángulos rectos?", "Cuadrado", "Rectángulo", "Triángulo equilátero", "Trapecio")
    val question9 = Question("Si una recta es paralela a otra, ¿cómo se llaman los ángulos que forman entre ellas cuando se corta con una transversal?", "Ángulos alternos internos", "Ángulos consecutivos", "Ángulos opuestos por el vértice", "Ángulos complementarios")
    val question10 = Question("¿Qué valor tiene el número e (la base del logaritmo natural) aproximadamente?", "2.71", "3.14", "1.61", "1.73")

}
object HistoricQuestions{
    val question1 = Question("¿Quién fue el primer presidente de los Estados Unidos?", "George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams")
    val question2 = Question("¿En qué año comenzó la Primera Guerra Mundial?", "1914", "1912", "1920", "1939")
    val question3 = Question("¿Qué civilización construyó las pirámides de Egipto?", "Los egipcios", "Los romanos", "Los griegos", "Los persas")
    val question4 = Question("¿Quién fue el líder de la Revolución Francesa?", "Maximilien Robespierre", "Napoleón Bonaparte", "Louis XVI", "Jean-Paul Marat")
    val question5 = Question("¿Qué imperio fue conocido por su vasto sistema de carreteras y su arquitectura en piedra?", "El Imperio Romano", "El Imperio Azteca", "El Imperio Inca", "El Imperio Mongol")
    val question6 = Question("¿En qué año se firmó la Declaración de Independencia de los Estados Unidos?", "1776", "1783", "1800", "1791")
    val question7 = Question("¿Quién fue el famoso líder militar de la antigua Macedonia?", "Alejandro Magno", "César Augusto", "César", "Filipo II")
    val question8 = Question("¿Qué país fue el epicentro de la Revolución Industrial?", "Reino Unido", "Francia", "Alemania", "Estados Unidos")
    val question9 = Question("¿Quién fue el dictador de la Unión Soviética durante la Segunda Guerra Mundial?", "Stalin", "Lenin", "Jruschov", "Brezhnev")
    val question10 = Question("¿Qué gran imperio fue derrotado en la Batalla de Waterloo?", "El Imperio Napoleónico", "El Imperio Romano", "El Imperio Bizantino", "El Imperio Francés")

}
object GeneralQuestions{
    val question1 = Question("¿Cuál es el país más grande del mundo por superficie?", "Rusia", "Canadá", "China", "Estados Unidos")
    val question2 = Question("¿Quién pintó la Mona Lisa?", "Leonardo da Vinci", "Pablo Picasso", "Vincent van Gogh", "Claude Monet")
    val question3 = Question("¿En qué continente se encuentra Egipto?", "África", "Asia", "Europa", "Oceanía")
    val question4 = Question("¿Cuál es el océano más grande del planeta?", "Pacífico", "Atlántico", "Índico", "Ártico")
    val question5 = Question("¿En qué año se llegó a la luna por primera vez?", "1969", "1960", "1965", "1972")
    val question6 = Question("¿Qué es la 'puerta de Brandeburgo'?", "Un monumento", "Un palacio", "Una torre", "Una iglesia")
    val question7 = Question("¿Quién escribió 'Don Quijote de la Mancha'?", "Miguel de Cervantes", "Lope de Vega", "Garcilaso de la Vega", "Francisco de Quevedo")
    val question8 = Question("¿Qué instrumento musical tiene 88 teclas?", "Piano", "Guitarra", "Violín", "Batería")
    val question9 = Question("¿Qué animal es conocido por su capacidad para cambiar de color?", "Camaleón", "Perro", "Gato", "Conejo")
    val question10 = Question("¿Cuál es el río más largo del mundo?", "Amazonas", "Nilo", "Yangtsé", "Misisipi")

}
object SportsQuestions{
    val question1 = Question("¿En qué deporte se utiliza un balón ovalado?", "Rugby", "Fútbol", "Baloncesto", "Tenis")
    val question2 = Question("¿Qué país ganó la Copa Mundial de Fútbol de 2018?", "Francia", "Brasil", "Alemania", "Argentina")
    val question3 = Question("¿En qué ciudad se celebraron los Juegos Olímpicos de 2008?", "Pekín", "Londres", "Río de Janeiro", "Sydney")
    val question4 = Question("¿Qué jugador de tenis es conocido como el 'Rey de la arcilla'?", "Rafael Nadal", "Roger Federer", "Novak Djokovic", "Andy Murray")
    val question5 = Question("¿En qué deporte se utiliza una raqueta y un volante?", "Bádminton", "Fútbol", "Golf", "Tenis")
    val question6 = Question("¿Cuántos jugadores hay en un equipo de baloncesto en la cancha?", "5", "6", "7", "4")
    val question7 = Question("¿Cuál es el nombre del famoso torneo de tenis que se juega en césped en Londres?", "Wimbledon", "Roland Garros", "US Open", "Australian Open")
    val question8 = Question("¿Quién ostenta el récord de más goles en la historia de la Copa del Mundo de Fútbol?", "Pelé", "Diego Maradona", "Lionel Messi", "Marta")
    val question9 = Question("¿En qué deporte se celebran los 'Super Bowls'?", "Fútbol americano", "Béisbol", "Fútbol", "Baloncesto")
    val question10 = Question("¿Cuántos puntos vale un gol en el fútbol?", "1", "2", "3", "5")

}
object ScienceQuestions{
    val question1 = Question("¿Cuál es el elemento químico con el símbolo H?", "Hidrógeno", "Helio", "Hafnio", "Hematita")
    val question2 = Question("¿Qué planeta es conocido como el 'planeta rojo'?", "Marte", "Venus", "Júpiter", "Saturno")
    val question3 = Question("¿Cuál es la función principal de los glóbulos rojos?", "Transportar oxígeno", "Defender el cuerpo", "Producir hormonas", "Regular la temperatura")
    val question4 = Question("¿Qué órgano humano se encarga de bombear la sangre?", "Corazón", "Estómago", "Pulmón", "Riñón")
    val question5 = Question("¿Qué gas es necesario para la fotosíntesis?", "Dióxido de carbono", "Oxígeno", "Nitrógeno", "Helio")
    val question6 = Question("¿Cuántos huesos tiene el cuerpo humano adulto?", "206", "210", "220", "230")
    val question7 = Question("¿Quién propuso la teoría de la evolución por selección natural?", "Charles Darwin", "Isaac Newton", "Albert Einstein", "Galileo Galilei")
    val question8 = Question("¿Qué es la fuerza que mantiene los objetos pegados a la Tierra?", "Gravedad", "Magnetismo", "Electromagnetismo", "Fricción")
    val question9 = Question("¿Qué tipo de célula es la más abundante en el cuerpo humano?", "Células sanguíneas", "Células musculares", "Células nerviosas", "Células epiteliales")
    val question10 = Question("¿Qué es la teoría del Big Bang?", "La expansión del universo desde un solo punto", "El origen de las estrellas", "La formación de los agujeros negros", "El fin del universo")

}
private class PlayTrivial: ViewModel() {
    var questionsScience = listOf(
        ScienceQuestions.question1,
        ScienceQuestions.question2,
        ScienceQuestions.question3,
        ScienceQuestions.question4,
        ScienceQuestions.question5,
        ScienceQuestions.question6,
        ScienceQuestions.question7,
        ScienceQuestions.question8,
        ScienceQuestions.question9,
        ScienceQuestions.question10
    )
    var questionsSports = listOf(
        SportsQuestions.question1,
        SportsQuestions.question2,
        SportsQuestions.question3,
        SportsQuestions.question4,
        SportsQuestions.question5,
        SportsQuestions.question6,
        SportsQuestions.question7,
        SportsQuestions.question8,
        SportsQuestions.question9,
        SportsQuestions.question10
    )
    val questionsGeneral = listOf(
        GeneralQuestions.question1,
        GeneralQuestions.question2,
        GeneralQuestions.question3,
        GeneralQuestions.question4,
        GeneralQuestions.question4,
        GeneralQuestions.question5,
        GeneralQuestions.question6,
        GeneralQuestions.question7,
        GeneralQuestions.question8,
        GeneralQuestions.question9,
        GeneralQuestions.question10
    )
    val questionsMath = listOf(
        MathematicQuestions.question1,
        MathematicQuestions.question2,
        MathematicQuestions.question3,
        MathematicQuestions.question4,
        MathematicQuestions.question5,
        MathematicQuestions.question6,
        MathematicQuestions.question7,
        MathematicQuestions.question8,
        MathematicQuestions.question9,
        MathematicQuestions.question10
    )
    val questionsHistor = listOf(
        HistoricQuestions.question1,
        HistoricQuestions.question2,
        HistoricQuestions.question3,
        HistoricQuestions.question4,
        HistoricQuestions.question5,
        HistoricQuestions.question6,
        HistoricQuestions.question7,
        HistoricQuestions.question8,
        HistoricQuestions.question9,
        HistoricQuestions.question10
    )
    var categories = listOf(ScienceQuestions, SportsQuestions, GeneralQuestions, MathematicQuestions, HistoricQuestions)
    var category = mutableStateOf(categories.random())
    var currentQuestion = questionsHistor.random()
    var answers = listOf(currentQuestion.correctAnswer, currentQuestion.incorrectAnswer, currentQuestion.incorrectAnswerTwo, currentQuestion.incorrectAnswerThree).shuffled(random = Random)
    var count = mutableStateOf(0)
    var dificulty = mutableStateOf(1)
    var rounds = mutableStateOf(5)
    var time = mutableStateOf(10)
    var correct = mutableStateOf(false)
    fun randomQuestion(){
        category= mutableStateOf(categories.random())
        when(category.toString()){
            "ScienceQuestions" -> currentQuestion = questionsScience.random()
            "SportsQuestions" -> currentQuestion = questionsSports.random()
            "GeneralQuestions" -> currentQuestion = questionsGeneral.random()
            "MathematicQuestions" -> currentQuestion = questionsMath.random()
            "HistoricQuestions" -> currentQuestion = questionsHistor.random()
        }
        answers = listOf(currentQuestion.correctAnswer, currentQuestion.incorrectAnswer, currentQuestion.incorrectAnswerTwo, currentQuestion.incorrectAnswerThree).shuffled(random = Random)
        correct.value = false
    }
    fun comprvCorrect(value: String ){
        if(value == currentQuestion.correctAnswer){
            correct.value = true
        }
    }
    fun time(){
        time.value --
    }
}
@Composable
fun countDown(){
    val model = viewModel{ PlayTrivial() }
    LaunchedEffect(key1 = model.time.value){
        while(model.time.value > 0){
            delay(1000L)
            model.time()
        }
    }
}
@Composable
fun ScreenTrivial(navigateToScreenEnd: () -> Unit){
    val model = viewModel { PlayTrivial() }
    model.randomQuestion()
    Column{
            Text(model.time.value.toString())
            Text(model.currentQuestion.question)
        Row{
            for(i in 0..1){
                buttons(model::comprvCorrect, model::randomQuestion, model.answers[i])
            }
        }
        Row{
            for(i in 2..3){
                buttons(model::comprvCorrect, model::randomQuestion, model.answers[i])
            }
        }

        Button(onClick = {navigateToScreenEnd()}){
            Text("Exit")
        }
    }



}
@Composable
fun buttons(onClick: (String) ->Unit, randomQuestion: () -> Unit, message:String ){
    val model = viewModel { PlayTrivial() }
    Button(onClick = {onClick(message); randomQuestion()}){
        Text(message)
    }



}

@Composable
fun buttonExit(navigateToScreenTrivial: () -> Unit){
    Button(onClick = navigateToScreenTrivial){
        Text("NextQuestion")
    }
}
@Composable
fun ScreenInici(navigateToScreenTrivial: () -> Unit){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("Screen 1")
        Button(onClick = {navigateToScreenTrivial()}){
            Text("Play")
        }
    }

}
@Composable
fun ScreenEnd(navigateToScreenInici: ()-> Unit){
    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text("pumba")
        Text("Screen 3")
        Button(onClick = {navigateToScreenInici()}) {
            Text("main menu")}
    }}
@Composable
fun ScreenSettings(navigateToScreenInici: () -> Unit){

}
@Composable
fun TrivialScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Trivial.ScreenTrivial) {

        composable<Trivial.ScreenInici> {
            ScreenInici{navController.navigate(Trivial.ScreenTrivial)}
        }
        composable<Trivial.ScreenTrivial> {
            ScreenTrivial(
                navigateToScreenEnd = {navController.navigate(Trivial.ScreenEnd)}

            )
        }
        composable<Trivial.ScreenEnd> {
            ScreenEnd{ navController.navigate(Trivial.ScreenInici) }
        }
        composable<Trivial.ScreenSettings> {
            ScreenSettings{navController.navigate(Trivial.ScreenInici)}
        }
    }
}