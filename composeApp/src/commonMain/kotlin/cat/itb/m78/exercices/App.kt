package cat.itb.m78.exercices


import androidx.compose.runtime.*
import cat.itb.m78.exercices.Navegació.LibNavScreenSample
import cat.itb.m78.exercices.Navegació.ManualNav
import cat.itb.m78.exercices.Navegació.ScreenPlay
import cat.itb.m78.exercices.Navegació.TikTak
import cat.itb.m78.exercices.Navegació.TikTakScreenSample

import cat.itb.m78.exercices.State.Good
import cat.itb.m78.exercices.State.Hello
import cat.itb.m78.exercices.State.SecretNumber
import cat.itb.m78.exercices.State.diceRoller
import cat.itb.m78.exercices.ViewModel.count
import cat.itb.m78.exercices.ViewModel.shopping
import cat.itb.m78.exercices.theme.AppTheme

@Composable
internal fun App() = AppTheme {
    TikTakScreenSample()
}
