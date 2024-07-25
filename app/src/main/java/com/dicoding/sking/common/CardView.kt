package com.dicoding.sking.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import android.util.Log

@Composable
fun CardViewContent(recModel: RecModel, navController: NavController) {
    var isClicked by remember { mutableStateOf(false) }

    Log.d("CardViewContent", "Displaying card for: ${recModel.heading}")

    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clickable {
                navController.navigate("card_detail/${recModel.heading}")
                isClicked = !isClicked
                Log.d("CardViewContent", "Card clicked: ${recModel.heading}, isClicked: $isClicked")
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isClicked) Color.Gray else Color(0xFF383737))
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(model = recModel.photoUrl)
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(85.dp)
                    .background(Color.Gray)
            )
            if (painter.state is AsyncImagePainter.State.Loading) {
                Log.d("CardViewContent", "Image loading: ${recModel.photoUrl}")
            } else if (painter.state is AsyncImagePainter.State.Error) {
                Log.e("CardViewContent", "Image loading error: ${recModel.photoUrl}")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = recModel.heading,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left
                )
                Text(
                    text = recModel.about,
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardViewContent() {
    val navController = rememberNavController()
    val recModel = RecModel(
        heading = "Melanoma",
        photoUrl = "https://example.com/sample.jpg", // Replace with your image URL
        about = "Melanoma is skin cancer ..."
    )
    CardViewContent(recModel = recModel, navController = navController)
}
