package com.dicoding.sking.common

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dicoding.sking.R

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem("home", R.drawable.baseline_home_24, "main_screen"),
        BottomNavItem("result", R.drawable.baseline_result_24, "details_screen"),
        BottomNavItem("info", R.drawable.baseline_info_24, "info_screen")
    )
}

// Data class for BottomNavItem
data class BottomNavItem(val label: String, val icon: Int, val route: String)

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomNavigation(
        modifier = Modifier
            .height(65.dp)
            .clip(RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)),
        backgroundColor = Color(0xFFFFCDD3),
        elevation = 10.dp

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.label,
                        tint = if (currentRoute == navItem.route) Color.Black else Color.Gray
                    )
                },
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false,

            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val navGraph = rememberNavController()
    BottomNavigationBar(navGraph)
}
