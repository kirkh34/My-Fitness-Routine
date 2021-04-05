package com.example.myfitnessroutine.data

import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Routine (
    var id: String,
    var name: String,
    var exercises : MutableList<String>
) : Serializable