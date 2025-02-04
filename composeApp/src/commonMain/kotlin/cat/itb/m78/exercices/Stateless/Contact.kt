package cat.itb.m78.exercices.Stateless

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import m78exercices.composeapp.generated.resources.Res
import m78exercices.composeapp.generated.resources.myImage
import org.jetbrains.compose.resources.painterResource
data class Contact(val fullName: String, val email: String, val phone: String)val contact = Contact("Marta Casserres", "marta@example.com", "934578484")
@Composable
fun Contact(){
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(painter = painterResource(Res.drawable.myImage), contentDescription = null, modifier = Modifier.size(200.dp).border(2.dp, androidx.compose.ui.graphics.Color.White, CircleShape).clip(CircleShape))
        Text(contact.fullName, fontSize = 2.em, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))
        Card{
            Column(modifier = Modifier.padding(20.dp)){
                Row(modifier = Modifier.padding(bottom = 10.dp)){
                    Icon(Icons.Default.Email, "email")
                    Text(contact.email)
                }
                Row(modifier = Modifier.padding(bottom = 10.dp)){
                    Icon(Icons.Default.Call, "call")
                    Text(contact.phone)
                }
            }

        }

    }

}