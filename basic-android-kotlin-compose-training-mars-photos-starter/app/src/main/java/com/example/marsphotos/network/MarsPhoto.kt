package com.example.marsphotos.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/*
Stage 8 of the tutorial:
- data classes are used to store data. this one represents a single photo
- serializable allows the data to be converted to JSON
 */
@Serializable
data class MarsPhoto (
    val id: String,
    /*
    SerialName is used because the name of the JSON is img_src, but Kotlin variable name is imgSrc
    In Kotlin, the variables only use concatenated uppercase or lowercase letters
     */
    @SerialName(value = "img_src")
    val imgSrc: String
)