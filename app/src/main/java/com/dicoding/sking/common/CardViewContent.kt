package com.dicoding.sking.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun CardViewContent(
    progress: Int,
    headingText: String,
    buttonText: String,
    showDialog: () -> Unit,

) {

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray, //Card background color
            contentColor = Color.White  //Card content color,e.g.text
        )

    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .height(138.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        CircularProgressIndicator(
                            progress = progress / 100f,
                            modifier = Modifier
                                .size(100.dp)
                        )
                        Text(
                            text = "$progress%",
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = headingText,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(start = 26.dp)
                        )
                        Button(
                            onClick = { showDialog() },
                            modifier = Modifier
                                .padding(top = 4.dp, start = 20.dp) ,
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFCDD3))
                        ) {
                            Text(
                                text = buttonText,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}



