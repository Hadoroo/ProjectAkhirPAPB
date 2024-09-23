package com.example.kasir

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kasir.ui.theme.KasirTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KasirTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Topbar(onClick = {})
                    val scrollState = rememberScrollState()
                    Home(onClick = {}, scrollState = scrollState)
                    Navbar(onClick = {})
                }
            }
        }
    }
}

@Composable
fun Navbar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .shadow(elevation = 100.dp)
                .align(Alignment.BottomCenter)
                .height(70.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFD9D9D9))
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextButton(
                    onClick = { onClick() },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize(),
                    shape = RectangleShape
                ) {
                    val historyIcon = painterResource(id = R.drawable.history)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Icon(
                            painter = historyIcon,
                            contentDescription = "Fast food icon",
                            modifier = Modifier
                                .size(35.dp)
                                .weight(2f),
                            tint = Color.Black
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
                TextButton(
                    onClick = { onClick() },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize(),
                    shape = RectangleShape
                ) {
                    val menuIcon = painterResource(id = R.drawable.menu)
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = menuIcon,
                            contentDescription = "Fast food icon",
                            modifier = Modifier
                                .size(35.dp)
                                .weight(2f),
                            tint = Color.Black
                        )
                    }
                }
            }
        }
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .size(100.dp)
                .offset(y = (-10).dp)
                .align(Alignment.BottomCenter),
            shape = CircleShape,
            colors = ButtonDefaults
                .buttonColors(containerColor = Color(0xFFB20000))
        ) {
            val pesananIcon = painterResource(id = R.drawable.fastfood)
            Icon(painter = pesananIcon,
                contentDescription = "Fast food icon",
                modifier = Modifier.size(45.dp))
        }
    }
}

@Composable
fun Topbar(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .shadow(elevation = 100.dp)
                .align(Alignment.TopEnd)
                .height(70.dp)
                .fillMaxWidth()
                .background(color = Color(0xFFD9D9D9))
        ) {
            Row (modifier = Modifier.fillMaxWidth()){
                Column (modifier = Modifier
                    .weight(5f)
                    .fillMaxHeight()
                ){
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .offset(x = 10.dp)
                            .wrapContentSize(Alignment.Center),
                        text = "Senin, 23 September 2024",
                        fontSize = 15.sp
                    )
                    Text(
                        modifier = Modifier
                            .weight(3f)
                            .offset(x = 10.dp)
                            .wrapContentSize(Alignment.Center),
                        text = "Rp. 1.300.000,-",
                        fontSize = 30.sp
                    )
                }
                TextButton(
                    onClick = { onClick() },
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentSize(Alignment.Center)
                        .fillMaxSize(),
                    shape = RectangleShape
                ) {
                    val printIcon = painterResource(id = R.drawable.print)
                    Icon(
                        painter = printIcon,
                        contentDescription = "Fast food icon",
                        modifier = Modifier
                            .size(35.dp)
                            .weight(4f),
                        tint = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun Home(onClick: () -> Unit, modifier: Modifier = Modifier, scrollState: ScrollState) {
    Box(modifier = Modifier
        .fillMaxSize()
        .offset(y = 70.dp)

        .verticalScroll(scrollState)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.Blue)
        ) {
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(120.dp),
                onClick = onClick
            )
            {
                Text("test")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(120.dp),
                onClick = onClick
            )
            {

            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(120.dp),
                onClick = onClick
            )
            {
                Text("test")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(120.dp),
                onClick = onClick
            )
            {

            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(120.dp),
                onClick = onClick
            )
            {
                Text("test")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(120.dp),
                onClick = onClick
            )
            {

            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(120.dp),
                onClick = onClick
            )
            {
                Text("test")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(120.dp),
                onClick = onClick
            )
            {

            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                    .height(120.dp),
                onClick = onClick
            )
            {
                Text("test")
            }
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray)
                    .height(120.dp),
                onClick = onClick
            )
            {

            }
        }
    }
}
