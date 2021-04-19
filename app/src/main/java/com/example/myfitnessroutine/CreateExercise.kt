package com.example.myfitnessroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.DataRepository
import com.example.myfitnessroutine.data.uniqueID
import java.util.*

class CreateExercise : AppCompatActivity()
{
    private lateinit var dataRepo : DataRepository
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>
    private lateinit var exerciseName: EditText
    private lateinit var exerciseTime: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)

        initRepo()

        exerciseName = findViewById(R.id.editTextExerciseName)
        exerciseTime = findViewById(R.id.editTextExerciseTime)


        val btnCreateExercise = findViewById<Button>(R.id.createExercise)
        btnCreateExercise.setOnClickListener{
            val exerciseNameValue = exerciseName.text.toString()
            val exerciseTimeValue = exerciseTime.text.toString().toInt()
            val uniqueID: String = UUID.randomUUID().toString()

            val exercise = Exercise(uniqueID, exerciseNameValue, exerciseTimeValue)
            exerciseList.add(exerciseList.size,exercise)
            dataRepo.saveExerciseData(exerciseList)
            this.finish()
        }

    }

    private fun initRepo(){
        dataRepo = DataRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }
}