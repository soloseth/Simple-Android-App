package com.example.tipper

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipper.ui.theme.TipperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        val people = listOf(
//            person("John", "Doe", 30),
  //          person("Jane", "Doe", 25),
    //        person("Bob", "Smith", 10),
//            person("John", "Doe", 30),
//            person("Jane", "Doe", 18),
//            person("Bob", "Smith", 20)
//        )

  //      val peopleFiltered = people.filter { it.age > 21 }

        val rssItems = listOf(
            RSSitem("Welcome to Austin, Texas! We have music!", "There is lots of music here in Austin, Texas.", RSSType.TEXT),
            RSSitem("Welcome to my photo gallery. View photos", "Click here to view photos", RSSType.IMAGE,  R.drawable.istockphoto),
            RSSitem("Press conference happening now", "Click here to watch live", RSSType.VIDEO, R.drawable.shutterstock)

        )


        setContent {
            TipperTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        LazyColumn {
                            items(rssItems) {
                                when (it.type) {
                                    RSSType.TEXT -> {
                                        RSSItemText(it)
                                    }

                                    RSSType.VIDEO -> {
                                        RSSItemVideo(it)
                                    }

                                    RSSType.IMAGE -> {
                                        RSSItemImage(it)
                                    }
                                }
                            }
                        }
                    }
                    Searchbox()
                }
            }
        }
    }
}


@Composable
fun Searchbox() {
    var searchQuery by remember { mutableStateOf("") }
    TextField(
        value = searchQuery,
        label = { Text("Search") },
        onValueChange = {
            searchQuery = it
        },
        modifier = Modifier.padding(top = 30.dp).fillMaxWidth()
    )
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

@Composable
fun RSSItemText(rssItem: RSSitem) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            text = rssItem.title
        )
        Text(
            modifier = Modifier.padding(12.dp),
            text = rssItem.text
        )
    }
}

@Composable
fun RSSItemVideo(rssItem: RSSitem) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(24.dp),
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            text = rssItem.title
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = rssItem.text
        )
        rssItem.media?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Photo of Person",
                modifier = Modifier.fillMaxSize()
                )
        }
    }
}

@Composable
fun RSSItemImage(rssItem: RSSitem) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxSize()
    ) {
        Text(
            modifier = Modifier.padding(24.dp).clickable{
                println("Clicked")
            },
            fontSize = 32.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Black,
            text = rssItem.title
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = rssItem.text
        )
        rssItem.media?.let { photo ->
            Image(
                painter = painterResource(id = photo),
                contentDescription = "Photo of Person",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

