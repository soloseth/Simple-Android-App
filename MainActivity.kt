package com.example.tipper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipper.ui.theme.TipperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val people = listOf(
            person("John", "Doe", 30),
            person("Jane", "Doe", 25),
            person("Bob", "Smith", 10),
            person("John", "Doe", 30),
            person("Jane", "Doe", 18),
            person("Bob", "Smith", 20)
        )

        val peopleFiltered = people.filter { it.age > 21 }


        setContent {
            TipperTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    LazyColumn {
                        items(peopleFiltered) {
                            CardView(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipperTheme {
        Greeting("Solomon")
    }
}

@Composable
fun CardView(person: person) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_person_24),
                contentDescription = null,
                modifier = Modifier.width(100.dp).height(100.dp)
            )
            Column {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = person.firstName
                )
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = person.lastName
                )
                Text(
                    modifier = Modifier.padding(0.dp),
                    text = "Age: " + person.age
                )
            }

        }
    }
}