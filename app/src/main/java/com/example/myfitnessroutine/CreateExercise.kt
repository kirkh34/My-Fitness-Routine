package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.RoutineRepository
import java.util.*

class CreateExercise : AppCompatActivity()
{
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val exerciseList = viewModel.exerciseData
        val uniqueID = UUID.randomUUID().toString()
        val exercise = Exercise(uniqueID, "Jumping Jacks", 120)
        exerciseList.add(exerciseList.size,exercise)

        val btnCreateExercise = findViewById<Button>(R.id.addExercise)
        btnCreateExercise.setOnClickListener{
            //Log.i("create", "exercise created: ${.name}")
            viewModel.updateExerciseData(exerciseList)
        }

    }
}