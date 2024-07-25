package com.dicoding.sking

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.sking.screen.detail.CardDetail
import com.dicoding.sking.common.Model
import com.dicoding.sking.common.RecModel
import com.dicoding.sking.screen.MainScreen
import com.dicoding.sking.screen.detail.RecViewList
import com.dicoding.sking.screen.detail.Results
import com.dicoding.sking.screen.infoApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController) }
        composable("details_screen") { Results(navController) }
        composable("info_screen") { infoApp(navController) }
        composable("rec_list") { RecViewList(navController) }
        composable("card_detail/{ID}") { backStackEntry ->
            val recId = backStackEntry.arguments?.getString("ID")
            val recModelState = remember { mutableStateOf<RecModel?>(null) }

            LaunchedEffect(recId) {
                if (recId != null) {
                    readData(recId) { recModel ->
                        recModelState.value = recModel
                    }
                }
            }

            if (recModelState.value != null) {
                recModelState.value?.let { recModel ->
                    CardDetail(recId = recId.toString(), recModel = recModel, navController = navController)
                }
            } else {
                // Optionally show a loading or error state here
                Log.d("NavigationHost", "Data not yet loaded or null for recId: $recId")
            }
        }
    }
}

fun readData(id: String, onDataReceived: (RecModel) -> Unit) {
    val DBKoneksi = FirebaseDatabase.getInstance().reference.child("kanker")
    DBKoneksi.child(id).addListenerForSingleValueEvent(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value = dataSnapshot.getValue(Model::class.java)
            if (value != null) {
                val recModel = RecModel(
              heading = value.nama ?: "",
                    about = value.des ?: "",
                    photoUrl = value.photo ?: ""
                )
                onDataReceived(recModel)
                Log.d("readData", "Data fetched for idContact: $id")
            } else {
                Log.d("readData", "No data found for idContact: $id")
            }
        }

        override fun onCancelled(databaseError: DatabaseError) {
            Log.e("readData", "Error fetching data", databaseError.toException())
        }
    })
}