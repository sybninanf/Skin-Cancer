package com.dicoding.sking.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.sking.NavigationHost
import com.dicoding.sking.common.BottomNavigationBar
import com.dicoding.sking.common.MainContent
import com.dicoding.sking.common.Navbar
import com.dicoding.sking.ui.theme.SkinGTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkinGTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    val context = LocalContext.current
                    val navController = rememberNavController()
//                    val navGraph = NavGraph(navController = navController)
                    Scaffold(
                        topBar = {
                            Navbar(navController)
                        },
                        bottomBar = {
                            BottomNavigationBar(navController)
                        },
                        content = { padding ->
                            NavigationHost(navController)
                        }
                    )
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@Preview(showBackground = true)
@Composable
fun MainScreen(navController: NavHostController) {
        SkinGTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black
            ) {
                Scaffold(
                    topBar = {
                        Navbar(navController)
                    },
                    bottomBar = {
                        BottomNavigationBar(navController)
                    },
                    content = {
                        MainContent()
                    })
            }
        }
    }

