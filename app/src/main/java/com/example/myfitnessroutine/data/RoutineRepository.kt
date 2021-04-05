package com.example.myfitnessroutine.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myfitnessroutine.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class RoutineRepository(val app: Application) {

    val routineData = MutableLiveData<List<Routine>>()
    val exerciseData = MutableLiveData<List<Exercise>>()
    var staticExerciseData : MutableList<Exercise> = mutableListOf()


    private val listTypeR = Types.newParameterizedType(
        List::class.java, Routine::class.java
    )
    private val listTypeE = Types.newParameterizedType(
        MutableList::class.java, Exercise::class.java
    )



    init {
        getRoutineData()
        getExerciseData()
    }

    fun getRoutineData(){
        val text = FileHelper.getTextFromAssets(app, "routines.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listTypeR)
        routineData.value = adapter.fromJson(text) ?: emptyList()
    }

    fun getExerciseData(){
        val text = FileHelper.getTextFromAssets(app, "exercises.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Exercise>> = moshi.adapter(listTypeE)
        exerciseData.value = adapter.fromJson(text) ?: emptyList()
        staticExerciseData =  adapter.fromJson(text) as MutableList<Exercise>? ?: mutableListOf()
    }

}