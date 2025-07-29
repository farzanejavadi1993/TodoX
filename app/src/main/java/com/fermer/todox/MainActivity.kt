package com.fermer.todox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fermer.auth.presentation.AuthViewModel
import com.fermer.auth.presentation.screen.SignInScreen
import com.fermer.auth.presentation.screen.SignUpScreen
import com.fermer.todox.ui.theme.TodoXTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        enableEdgeToEdge()

        setContent {
            val authViewModel: AuthViewModel = viewModel()

            SignInScreen  { email, password ->
                authViewModel.signIn(email, password)
            }


           /* val viewModel = TaskListViewModel()
            TaskListScreen(viewModel)
*/
         /*   TodoXTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }*/
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
    TodoXTheme {
        Greeting("Android")
    }
}