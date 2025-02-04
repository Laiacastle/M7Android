package cat.itb.m78.exercices.Navegació
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

object Destination {
    @Serializable
    data object ScreenDefault
    @Serializable
    data object Screen1
    @Serializable
    data object Screen2
    @Serializable
    data class Screen3(val message: String)
}
@Composable
fun LibNavScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Destination.ScreenDefault) {
        composable<Destination.ScreenDefault> {
            ScreenDefault(
            navigateToScreen1 = {navController.navigate(Destination.Screen1)},
            navigateToScreen2 = {navController.navigate(Destination.Screen2) },
            navigateToScreen3 = {navController.navigate(Destination.Screen3(it))})
        }
        composable<Destination.Screen1> {
            Screen1{navController.navigate(Destination.ScreenDefault)}
        }
        composable<Destination.Screen2> {
            Screen2 { navController.navigate(Destination.ScreenDefault) }
        }
        composable<Destination.Screen3> { backStack ->
            val message = backStack.toRoute<Destination.Screen3>().message
            Screen3(message){ navController.navigate(Destination.ScreenDefault) }
        }
    }
}

