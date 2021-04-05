package com.example.myfitnessroutine

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.RoutineRepository


class MainViewModel(app: Application): AndroidViewModel(app) {

    private val dataRepo = RoutineRepository(app)
    val routineData = dataRepo.routineData
    val exerciseData = dataRepo.exerciseData
    val staticExerciseData = dataRepo.staticExerciseData


    fun buildExList(exIdList: Array<String>) : MutableList<Exercise>{
        val result = mutableListOf<Exercise>()
        exIdList.forEach { exId ->
            staticExerciseData.forEach{
                if(it.id == exId){
                   result.add(it)
                }
            }
        }
        return result
    }

}