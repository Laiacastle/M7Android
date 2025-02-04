package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.myImage
import m78exercices.composeapp.generated.resources.resource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable

fun Resource(){
    Column{
        Text(stringResource(Res.string.resource))
        Image(painter = painterResource(Res.drawable.myImage), contentDescription = null, modifier = Modifier.size(400.dp).padding(40.dp))
    }


}