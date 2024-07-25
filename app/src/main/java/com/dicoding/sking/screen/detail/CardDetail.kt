package com.dicoding.sking.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.dicoding.sking.common.RecModel


@Composable
fun CardDetail(recId : String, recModel: RecModel, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp)
    ) {
        Spacer(modifier = Modifier.height(150.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp) // Adjust height as needed
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFF383737))
        ) {
            Image(
                painter = rememberImagePainter(data = recModel.photoUrl),// Replace with actual image resource ID
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Overview",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 4.dp) // Adjust spacing as needed
            )
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = recModel.heading,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = recModel.about,
                color = Color.Black,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardDetailViewPreview() {
    val navController = rememberNavController()
    val recModel = RecModel(
        heading = "HEADING",
        about = "A melanocytic nevus (also known as nevocytic nevus, nevus-cell nevus and commonly as a mole) is a type of melanocytic tumor that contains nevus cells.",
        photoUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/40/SolarAcanthosis.jpg/1200px-SolarAcanthosis.jpg"
    )

    CardDetail(
        recId = "sample_id",
        recModel = recModel,
        navController = navController
    )
}
