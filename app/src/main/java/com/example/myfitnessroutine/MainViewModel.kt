package com.example.myfitnessroutine

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository


class MainViewModel(app: Application): AndroidViewModel(app) {

    private val dataRepo = RoutineRepository(app)
    var routineData : MutableList<Routine> = mutableListOf()
    var exerciseData : MutableList<Exercise> = mutableListOf()
    //val staticExerciseData = dataRepo.staticExerciseData

    init {
        getData()
    }

    fun buildExList(exIdList: MutableList<String>) : MutableList<Exercise>{
        val result = mutableListOf<Exercise>()
        exIdList.forEach { exId ->
            Log.i("loop id","${exId}")
            exerciseData.forEach{
                if(it.id == exId){
                   result.add(it)
                }
            }
        }
        return result
    }

    fun getData(){
        routineData = dataRepo.routineData
        exerciseData = dataRepo.exerciseData
    }

    fun getRoutine(): MutableList<Routine>{
        Log.i("getRoutine-mainview", routineData.toString())
        return routineData
    }

    fun updateRoutineData(updatedList: MutableList<Routine>){
        routineData = updatedList
        dataRepo.saveRoutineData(updatedList)
        Log.i("update", routineData.toString())
        Log.i("update", "list updated")
    }

    fun updateExerciseData(updatedList: MutableList<Exercise>){
        exerciseData = updatedList
        dataRepo.saveExerciseData(updatedList)
        Log.i("update", exerciseData.toString())

        Log.i("update", "list updated")
    }

}