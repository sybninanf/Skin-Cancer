package com.dicoding.sking.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.sking.ui.theme.SkinGTheme
import com.dicoding.sking.R

@Composable
fun Navbar(navController: NavHostController) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(11.dp) // Height of the bullet point
                .background(Color(0xFFFFCDD3)) // Color of the bullet point
        )
    TopAppBar(
        modifier = Modifier.clip( shape = RoundedCornerShape( bottomEnd = 200.dp)),
        elevation = 8.dp,
        backgroundColor = Color(0xFFFFCDD3),
        contentPadding = PaddingValues(horizontal = 26.dp, vertical = 40.dp), // Set padding
    ) {
            Column {
                Text(
                    text = "SkinG",
                    fontSize = 35.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Diagnose Cancer",
                    fontSize = 10.sp,
                    color = Color.Black,
                )
            }

        }
}}

@Preview(showBackground = true)
@Composable
fun NavbarPreview() {
    SkinGTheme {
        val navController = rememberNavController()
        Navbar(navController)
    }
}
