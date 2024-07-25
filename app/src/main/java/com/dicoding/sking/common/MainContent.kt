package com.dicoding.sking.common

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.dicoding.sking.R
import com.dicoding.sking.ml.Model
import com.dicoding.sking.ui.theme.SkinGTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.IOException

@Composable
fun MainContent() {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageBitmap by remember { mutableStateOf<Bitmap?>(null) }
    var predictionResult by remember { mutableStateOf<String?>(null) }
    var predictionProgress by remember { mutableStateOf(0) }
    val context = LocalContext.current
    var showCard by remember { mutableStateOf(false) }
    val alertDialogShown = remember { mutableStateOf(false) }
    var title by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    val imagePickerLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            selectedImageBitmap = bitmap
        }
    }
    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap: Bitmap? ->
            bitmap?.let {
                selectedImageBitmap = it
            }
        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { granted ->
            if (granted) {
                takePictureLauncher.launch(null)
            }
        }
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(26.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        ImagePickerCard(
            imagePickerLauncher = imagePickerLauncher,
            selectedImageBitmap = selectedImageBitmap,
            takePictureLauncher = takePictureLauncher,
            cameraPermissionLauncher = cameraPermissionLauncher,
            onImageSelected = { bitmap ->
                selectedImageBitmap = bitmap
            }
        )
        Spacer(modifier = Modifier.height(30.dp))

        PredictionButton(
            selectedImageBitmap = selectedImageBitmap,
            context = context,
            onResult = { result -> predictionResult = result },
            onProgress = { progress -> predictionProgress = progress },
            onTitle = { newTitle -> title = newTitle },
            onMessage = { newMessage -> message = newMessage },
            onShowCard = { shouldShow -> showCard = shouldShow }
        )

        Spacer(modifier = Modifier.height(30.dp))

        if (showCard) {
            predictionResult?.let {
                CardViewContent(
                    progress = predictionProgress,
                    headingText = it,
                    buttonText = "READ MORE",
                    showDialog = { alertDialogShown.value = true }
                )
            }
        }

        if (alertDialogShown.value) {
            AlertDialog(
                onDismissRequest = { alertDialogShown.value = false },
                title = { androidx.compose.material.Text(text = title) },
                text = { androidx.compose.material.Text(text = message) },
                confirmButton = {
                    Button(
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFCDD3)),
                        onClick = { alertDialogShown.value = false }
                    ) {
                        Text(text = "OK", color = Color.Black)
                    }
                },
                modifier = Modifier
                    .graphicsLayer {
                        alpha = if (alertDialogShown.value) 1f else 0f
                    }
            )
        }
    }
}

@Composable
fun ImagePickerCard(
    imagePickerLauncher: ActivityResultLauncher<String>,
    selectedImageBitmap: Bitmap?,
    onImageSelected: (Bitmap) -> Unit,
    takePictureLauncher: ActivityResultLauncher<Void?>,
    cameraPermissionLauncher: ActivityResultLauncher<String>,
) {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .size(230.dp)
            .padding(top = 36.dp)
            .clickable {
                showDialog = true
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            selectedImageBitmap?.let { bitmap ->
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(230.dp)
                        .align(Alignment.Center)
                )
            } ?: Image(
                painter = painterResource(id = R.drawable.img_add),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(230.dp)
                    .align(Alignment.Center)
            )
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Choose an option") },
            text = {
                Column {
                    Text(
                        text = "Take photo",
                        modifier = Modifier.clickable {
                            if (ContextCompat.checkSelfPermission(
                                    context,
                                    Manifest.permission.CAMERA
                                ) == PermissionChecker.PERMISSION_GRANTED
                            ) {
                                takePictureLauncher.launch(null)
                            } else {
                                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                            }
                            showDialog = false
                        }
                    )
                    Text(
                        text = "Choose from gallery",
                        modifier = Modifier.clickable {
                            // Launch file chooser logic here
                            imagePickerLauncher.launch("image/*")
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    colors = ButtonDefaults.buttonColors(Color(0xFFFFCDD3)),
                    onClick = { showDialog = false }
                ) {
                    Text("Cancel", color = Color.Black,  fontSize = 18.sp)
                }
            }
        )
    }
}


@Composable
fun PredictionButton(
    selectedImageBitmap: Bitmap?,
    context: Context,
    onResult: (String) -> Unit,
    onProgress: (Int) -> Unit,
    onTitle: (String) -> Unit,
    onMessage: (String) -> Unit,
    onShowCard: (Boolean) -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(Color(0xFFFFCDD3)),
        onClick = {
            selectedImageBitmap?.let { bitmap ->
                val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 28, 28, true)
                try {
                    val model = Model.newInstance(context)
                    val tensorImage = TensorImage(DataType.FLOAT32)
                    tensorImage.load(resizedBitmap)

                    val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, 28, 28, 3), DataType.FLOAT32)
                    val byteBuffer = tensorImage.buffer
                    inputFeature0.loadBuffer(byteBuffer)

                    val outputs = model.process(inputFeature0)
                    val outputFeature0 = outputs.outputFeature0AsTensorBuffer
                    val arr = outputFeature0.floatArray
                    val maxIndex = arr.indices.maxByOrNull { arr[it] } ?: -1
                    val result = Utils.hashMap[maxIndex] ?: "Unknown"
                    onResult(result)
                    val accuracy = arr[maxIndex] / arr.sum()
                    onProgress((accuracy * 100).toInt())
                    onTitle(Utils.hashMap[maxIndex] ?: "Unknown")
                    onMessage(Utils.hashMapInfo[maxIndex] ?: "No information available.")
                    onShowCard(true)
                    model.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                    onResult("Error: ${e.message}")
                } catch (e: Exception) {
                    e.printStackTrace()
                    onResult("Error: ${e.message}")
                }
            }
        }
    ) {
        Text("Predict", color = Color.Black,  fontSize = 18.sp )
    }
}


@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    SkinGTheme {
        MainContent()
    }
}
