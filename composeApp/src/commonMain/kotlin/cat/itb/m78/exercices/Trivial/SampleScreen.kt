package cat.itb.m78.exercices.T5ivial
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute


@Composable
fun TrivialScreenSample() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Trivial.ScreenInici) {

        composable<Trivial.ScreenInici> {
            ScreenInici(
                navigateToScreenCategory = {navController.navigate(Trivial.ScreenCategory)},
                navigateToScreenSettings = {navController.navigate(Trivial.ScreenSettings)})
        }
        composable<Trivial.ScreenTrivial> {
            ScreenTrivial(
                navigateToScreenEnd = {navController.navigate(Trivial.ScreenEnd(it))},
            )
        }
        composable<Trivial.ScreenEnd> {backStack ->
            val points = backStack.toRoute<Trivial.ScreenEnd>().points
            ScreenEnd(
                navigateToScreenInici = { navController.navigate(Trivial.ScreenInici) },
                points = points
            )
        }
        composable<Trivial.ScreenSettings> {
            ScreenSettings{navController.navigate(Trivial.ScreenInici)}
        }
        composable<Trivial.ScreenCategory>{
            ScreenCategory(
                navigateToScreenInici = {navController.navigate(Trivial.ScreenInici)},
                navigateToScreenTrivial = {navController.navigate(Trivial.ScreenTrivial)})
        }
    }
}