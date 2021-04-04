package com.example.myfitnessroutine.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Exercise (
    var id: String,
    var name: String,
    var time: Int
)