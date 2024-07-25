package com.dicoding.sking.screen.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.sking.common.Model
import com.dicoding.sking.common.RecModel

import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {

    private val _recModels = MutableStateFlow<List<RecModel>>(emptyList())
    val recModels: StateFlow<List<RecModel>> = _recModels

    init {
        fetchRecModels()
    }

    private fun fetchRecModels() {
        viewModelScope.launch {
            readAllData { recModels ->
                _recModels.value = recModels
            }
        }
    }
}
fun readAllData(onDataReceived: (List<RecModel>) -> Unit) {
    val DBKoneksi = FirebaseDatabase.getInstance().reference.child("kanker")
    DBKoneksi.get().addOnSuccessListener { dataSnapshot ->
        val recModels = mutableListOf<RecModel>()
        dataSnapshot.children.forEach { childSnapshot ->
            val value = childSnapshot.getValue(Model::class.java)
            if (value != null) {
                val recModel = RecModel(
                    heading = value.nama ?: "",
                    about = value.des ?: "",
                    photoUrl = value.photo ?: ""
                )
                recModels.add(recModel)
            }
        }
        onDataReceived(recModels)
    }.addOnFailureListener { exception ->
        Log.e("readAllData", "Error fetching data", exception)
    }
}
