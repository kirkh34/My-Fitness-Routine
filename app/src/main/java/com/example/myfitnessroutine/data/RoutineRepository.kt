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

    var routineData : MutableList<Routine> = mutableListOf()
    //val exerciseData = MutableLiveData<List<Exercise>>()
    var exerciseData : MutableList<Exercise> = mutableListOf()


    private val listTypeR = Types.newParameterizedType(
        MutableList::class.java, Routine::class.java
    )
    private val listTypeE = Types.newParameterizedType(
        MutableList::class.java, Exercise::class.java
    )



    init {
        readRoutineData()
        readExerciseData()
        //Log.i("log", "${exerciseData.value}")
    }

    /*
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
        //exerciseData.value = adapter.fromJson(text) ?: emptyList()
        //staticExerciseData =  adapter.fromJson(text) as MutableList<Exercise>? ?: mutableListOf()
    }

     */

    fun readRoutineData() {
        val json = FileHelper.readTextFile(app, "routines.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listTypeR)
        routineData = adapter.fromJson(json) as MutableList<Routine>? ?: mutableListOf()
    }

    fun saveRoutineData(routineData: MutableList<Routine>){
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listTypeR)
        val json = adapter.toJson(routineData)
        FileHelper.saveTextToFile(app, json, "routines.json")
    }

    fun readExerciseData() {
        val json = FileHelper.readTextFile(app, "exercises.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Exercise>> = moshi.adapter(listTypeE)
        exerciseData = adapter.fromJson(json) as MutableList<Exercise>? ?: mutableListOf()
    }

    fun saveExerciseData(exerciseData: MutableList<Exercise>){
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Exercise>> = moshi.adapter(listTypeE)
        val json = adapter.toJson(exerciseData)
        FileHelper.saveTextToFile(app, json, "exercises.json")
    }



}