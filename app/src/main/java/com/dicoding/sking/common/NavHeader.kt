package com.dicoding.sking.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dicoding.sking.R
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NavHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)  // Replace with your @dimen/nav_header_height value
            .background(Color(0xFFFFCDD3))
            .padding(
                start = 16.dp,
                top = 16.dp,    // Replace with your @dimen/activity_vertical_margin value
                end = 16.dp,    // Replace with your @dimen/activity_horizontal_margin value
                bottom = 16.dp  // Replace with your @dimen/activity_vertical_margin value
            ),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "",
            modifier = Modifier
                .width(126.dp)
                .padding(top = 8.dp)  // Replace with your @dimen/nav_header_vertical_spacing value
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavHeaderPreview() {
    MaterialTheme {
        NavHeader()
    }
}
