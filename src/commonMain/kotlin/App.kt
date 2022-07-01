import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    var checked by remember {
        mutableStateOf(false)
    }
    var clickCount by remember {
        mutableStateOf(0)
    }
    var text by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text("Compose for IoS")
                }
            )
        },
    ) {
        Column {
            OutlinedTextField(
                text,
                {
                    text = it
                }
            )
            Text("Hello Compose IoS!")
            Text("Click count: $clickCount")
            Button(onClick = {
                clickCount++
            }) {
                Text("Click me!")
            }
            Switch(checked = checked, onCheckedChange = { checked = it })
            CircularProgressIndicator()
            LinearProgressIndicator()
            Slider(value = 0.5f, onValueChange = {})
        }
    }
}