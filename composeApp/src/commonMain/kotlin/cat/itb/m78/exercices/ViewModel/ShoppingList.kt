package cat.itb.m78.exercices.ViewModel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import cat.itb.m78.exercices.Stateless.messages
import m78exercices.composeapp.generated.resources.Res
data class Products(val name : String, val amount: Int)
class ShopViewModel(): ViewModel(){
    var name = mutableStateOf("")
    var amount = mutableStateOf(0)
    var product = mutableStateOf(listOf<Products>())
    var it = 0
    fun onNameChange(valo : String){name.value = valo}
    fun onAmountChange(valo: String){amount.value = valo.toInt()}
    fun addProducts(){ if (name.value.isNotEmpty() && amount.value > 0) {
        product.value += Products(name.value, amount.value)
        name.value = ""
        amount.value = 0
    }}
}
@Composable
fun shopping(){
    val viewModel = viewModel { ShopViewModel() }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
        Card{
            shopping(viewModel.name.value, viewModel::onNameChange)
            shopping(viewModel.amount.value, viewModel::onAmountChange)
            Button(onClick = viewModel::addProducts ){
                Text("Add")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(Modifier.padding()){
            items(viewModel.product.value) { product ->
                Spacer(modifier = Modifier.height(20.dp))
                Card(modifier = Modifier.width(400.dp)){
                    Row(modifier = Modifier.padding( 20.dp)){
                        Text(product.name, fontWeight = FontWeight(23), textAlign = TextAlign.Right)
                        Spacer(modifier = Modifier.width(350.dp))
                        Text(product.amount.toString())
                    }
                }
            }
        }
    }

}
@Composable
fun shopping(name: String, onNameChange: (String)-> Unit){

    TextField(name,
        label = {Text("name") },
        onValueChange = onNameChange)
}
@Composable
fun shopping(amount: Int, onAmountChange: (String) ->Unit){
    var text by remember {mutableStateOf(amount.toString())}
    TextField(text,
        label = {Text("amount") },
        onValueChange = {text = it;onAmountChange(text) }
        )
}



