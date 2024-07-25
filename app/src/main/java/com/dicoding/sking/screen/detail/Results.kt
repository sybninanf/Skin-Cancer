package com.dicoding.sking.screen.detail


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.dicoding.sking.common.BottomNavigationBar
import com.dicoding.sking.common.CardViewContent
import com.dicoding.sking.common.Navbar
import com.dicoding.sking.ui.theme.SkinGTheme

@Composable
fun RecViewList(navController: NavController, recViewModel: DetailViewModel = viewModel()) {
    val recModels by recViewModel.recModels.collectAsState()

    LazyColumn (  modifier = Modifier
        .padding(top = 170.dp) // Adjust top padding based on AppBar height
        .padding(bottom = 56.dp) ) {
        items(recModels) { recModel ->
            CardViewContent(recModel, navController)
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Results(navController: NavHostController) {
    SkinGTheme {
        // A surface container using the 'background' color from the theme
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
                    RecViewList(navController)
                })
        }}
}
