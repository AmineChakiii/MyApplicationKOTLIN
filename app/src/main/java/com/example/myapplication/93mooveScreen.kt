

import android.R.attr.id
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.mooveScreen
import com.example.myapplication.ui.theme.CoursUiState
import com.example.myapplication.ui.theme.CoursViewModel
import com.example.myapplication.ui.theme.Datasource

@Composable
fun mooveApp(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = mooveScreen.Start.name, modifier = Modifier) {
        composable(route = mooveScreen.Start.name) {
            PageAccueil(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }
        composable(route = mooveScreen.Login.name) {
            FormulaireLogin(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                navController = navController
            )
        }
        composable(route = mooveScreen.AfficherCours.name) {
            ListeCours(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                Datasource.retourneListeCours(),
                navController =navController
            )


        }
        }
    }

@Composable
fun ListeCours(modifier: Modifier, listeCours: List<CoursUiState>, navController: NavHostController,) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(listeCours.size) { index -> val cours = listeCours[index]
            Spacer(modifier = Modifier.height(30.dp))
            Card() {Text(text = cours.prix.toString())}
            Spacer(modifier = Modifier.height(10.dp))
            Card() {Text(text = cours.nom)}
            Spacer(modifier = Modifier.height(10.dp))
                Card() {Text(text = cours.description)}

        }
    }

}

@Composable
fun FormulaireLogin(modifier: Modifier, navController: NavHostController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val backgroundColo = if (login.length >= 10) Color.Red else Color.White
    val backgroundColor = if (password.length >= 10) Color.Red else Color.White

    Column (modifier = modifier
            .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Card {TextField(
            label = { Text("Entrez votre identifiant") },
            value = login,
            onValueChange = {login = it},
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColo,
                unfocusedContainerColor = backgroundColo)

            )}

        Card {TextField(
            label = { Text("Entrez votre mot de pasee") },
            value = password,
            onValueChange = { password = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor)

            )}
        Button(onClick = {}
        ) {
            Text("Valider")
        }
    }
}

@Composable
fun PageAccueil(modifier: Modifier = Modifier, coursViewModel: CoursViewModel = viewModel(),navController: NavHostController = rememberNavController()) {
    val coursUiState by coursViewModel.uiState.collectAsState()

    val image = painterResource(R.drawable.logo)

      //  Text(coursUiState.nom)
    Column(
      modifier = modifier
          .fillMaxSize()
          .padding(16.dp),
       verticalArrangement = Arrangement.Center,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
        Image(
            painter = image,
            contentDescription = "logo 93moove",
            modifier = Modifier.size(150.dp)
        )
        Text(
            text = "93 moove " + "Bienvenue"
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(mooveScreen.AfficherCours.name) {
                popUpTo(mooveScreen.Start.name) { inclusive = false }
            }
        }) {
            Text("Consulter les cours")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(onClick = {
            navController.navigate(mooveScreen.Login.name) {
                popUpTo(mooveScreen.Start.name) {
                    inclusive = false
                }
            }
        }) {
            Text("se connecter")
        }

    }

}


