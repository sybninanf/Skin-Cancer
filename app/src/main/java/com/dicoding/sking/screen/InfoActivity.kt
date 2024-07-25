package com.dicoding.sking.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.sking.R
import com.dicoding.sking.common.BottomNavigationBar
import com.dicoding.sking.common.Navbar
import com.dicoding.sking.ui.theme.SkinGTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun infoApp(navController: NavHostController) {
    SkinGTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White  // Changed to white for better readability
        ) {
            Scaffold(
                topBar = {
                    Navbar(navController)
                },
                bottomBar = {
                    BottomNavigationBar(navController)
                },
                content = { padding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                            .padding(horizontal = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Info About This Application",
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                            Text(
                                text = stringResource(id = R.string.us),
                                color = Color.Black,
                                modifier = Modifier.padding(top = 20.dp),
                                textAlign = TextAlign.Justify
                            )
                        }
                            Image(
                                painter = painterResource(id = R.drawable.info),
                                contentDescription = "Info Image",
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .size(160.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.copyright),
                                color = Color.Black,
                                modifier = Modifier.padding(16.dp),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )

                    }
                }
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCardViewContent() {
    val navController = rememberNavController()
    infoApp( navController = navController)
}
