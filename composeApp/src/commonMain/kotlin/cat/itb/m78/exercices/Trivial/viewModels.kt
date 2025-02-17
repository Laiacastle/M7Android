package cat.itb.m78.exercices.T5ivial

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlin.random.Random

public class PlayTrivial: ViewModel() {
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

    var answers = mutableListOf(currentQuestion.value.correctAnswer, currentQuestion.value.incorrectAnswer, currentQuestion.value.incorrectAnswerTwo, currentQuestion.value.incorrectAnswerThree).shuffled(random = Random)
    var count = mutableStateOf(1)
    var countEncertades = mutableStateOf(0)
    val rounds = TrivialSettingsManager.get().rounds
    var time = mutableStateOf(TrivialSettingsManager.get().time)
    var correct = mutableStateOf(false)
    var clicado = mutableStateOf(false)
    val random = mutableStateOf(false)

    fun changeCategory(cate: TrivialSubject){
        when(cate){
            TrivialSubject.Math -> category.value = MathematicQuestions
            TrivialSubject.Sports -> category.value = SportsQuestions
            TrivialSubject.History -> category.value = HistoricQuestions
            TrivialSubject.General -> category.value = GeneralQuestions
            TrivialSubject.Science -> category.value = ScienceQuestions
            TrivialSubject.Random -> random.value = true
        }
    }

    fun randomQuestion(){
        if(count.value < rounds){
            if(random.value){
                category.value = mutableStateOf(categories.random())
            }
            when(category.value){
                ScienceQuestions -> currentQuestion.value = questionsScience.random()
                SportsQuestions -> currentQuestion.value = questionsSports.random()
                GeneralQuestions -> currentQuestion.value = questionsGeneral.random()
                MathematicQuestions -> currentQuestion.value = questionsMath.random()
                HistoricQuestions -> currentQuestion.value = questionsHistor.random()
            }
            answers = mutableListOf(currentQuestion.value.correctAnswer, currentQuestion.value.incorrectAnswer, currentQuestion.value.incorrectAnswerTwo, currentQuestion.value.incorrectAnswerThree).shuffled(random = Random)
            if(correct.value){
                countEncertades.value ++
            }
            count.value++
            correct.value = false
            clicado.value = false

        }
    }

    fun comprvCorrect(value: String ){
        if(value == currentQuestion.value.correctAnswer){
            correct.value = true
        }
    }
    fun minusTime(){
        time.value --
    }
    fun resetTime(){
        time.value = TrivialSettingsManager.get().time
    }
    fun click(){
        clicado.value = true
    }

}

public class SettingsViewModel: ViewModel(){
    private var difficulty = TrivialSettingsManager.get().difficulty
    private var rounds = TrivialSettingsManager.get().rounds
    private var time = TrivialSettingsManager.get().time
    fun updateDifficulty(diff: String){
        difficulty = diff
        saveSettings()
    }
    fun updateTime(tim: Int){
        time = tim
        saveSettings()
    }
    fun updateRounds(round: Int){
        rounds = round
        saveSettings()
    }
    fun saveSettings(){
        val newSett = TrivialSettings(difficulty, rounds, time)
        TrivialSettingsManager.update(newSett)
    }
}