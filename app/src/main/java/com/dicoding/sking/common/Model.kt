package com.dicoding.sking.common


data class Model(
    val id: String? = null,
    val nama: String? = null,
    val photo: String? = null,
    val des: String? = null,
    val additionalData: Map<String, Any>? = null
)
data class RecModel(
//    val id: String,
    val heading: String,
    val about: String,
    val photoUrl: String
)
