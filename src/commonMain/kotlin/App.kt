
import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.*
import kotlin.math.max

@Composable
fun App() {
    var checked by remember {
        mutableStateOf(false)
    }
    var clickCount by remember {
        mutableStateOf(0)
    }
    Scaffold (
        topBar = {
                 TopAppBar(
                 ) {
                     Text("Compose for IoS")
                 }
        },
        ) {
        Column {
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