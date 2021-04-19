package com.example.myfitnessroutine.data

import android.app.Application
import com.example.myfitnessroutine.utilities.FileHelper
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DataRepository(val app: Application) {

    var routineData : MutableList<Routine> = mutableListOf()
    var exerciseData : MutableList<Exercise> = mutableListOf()


    private val listTypeR = Types.newParameterizedType(
        MutableList::class.java, Routine::class.java
    )
    private val listTypeE = Types.newParameterizedType(
        MutableList::class.java, Exercise::class.java
    )



    init {
        //insertDemoData()
        readRoutineData()
        readExerciseData()
    }


    fun insertDemoData(){
        if(routineData.isEmpty()) {
            var demoExercise = Exercise("0000", "Demo Exercise", 30)
            var demoExerciseList: MutableList<Exercise> = mutableListOf()
            demoExerciseList.add(demoExercise)
            saveExerciseData(demoExerciseList)

            var demoRoutine = Routine("1111", "Demo Routine", mutableListOf("0000"))
            var demoRoutineList: MutableList<Routine> = mutableListOf()
            demoRoutineList.add(demoRoutine)
            saveRoutineData(demoRoutineList)
        }
    }

    fun getRoutine(routineID: String): Routine? {
        routineData.forEach {
            if(it.id == routineID){
                return it
            }
        }
        return null
    }

    fun buildRoutineExerciseList(routineID: String) : MutableList<Exercise>{
        val currRoutine = getRoutine(routineID)
        val result = mutableListOf<Exercise>()

        currRoutine?.exercises?.forEach { exId ->
            exerciseData.forEach{
                if(it.id == exId){
                    result.add(it)
                }
            }
        }
        return result
    }

    fun saveRoutineData(routineData: MutableList<Routine>){
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listTypeR)
        val json = adapter.toJson(routineData)
        FileHelper.saveTextToFile(app, json, "routines.json")
    }

    fun saveExerciseData(exerciseData: MutableList<Exercise>){
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Exercise>> = moshi.adapter(listTypeE)
        val json = adapter.toJson(exerciseData)
        FileHelper.saveTextToFile(app, json, "exercises.json")
    }

    private fun readRoutineData() {
        val json = FileHelper.readTextFile(app, "routines.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Routine>> = moshi.adapter(listTypeR)
        routineData = adapter.fromJson(json) as MutableList<Routine>? ?: mutableListOf()
    }

    private fun readExerciseData() {
        val json = FileHelper.readTextFile(app, "exercises.json")
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val adapter: JsonAdapter<List<Exercise>> = moshi.adapter(listTypeE)
        exerciseData = adapter.fromJson(json) as MutableList<Exercise>? ?: mutableListOf()
    }





}