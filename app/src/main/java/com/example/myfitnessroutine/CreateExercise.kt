package com.example.myfitnessroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.DataRepository
import com.example.myfitnessroutine.data.uniqueID

class CreateExercise : AppCompatActivity()
{
    private lateinit var dataRepo : DataRepository
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_exercise)

        initRepo()

        val exercise = Exercise(uniqueID, "Jumping Jacks", 120)
        exerciseList.add(exerciseList.size,exercise)

        val btnCreateExercise = findViewById<Button>(R.id.addExercise)
        btnCreateExercise.setOnClickListener{
            dataRepo.saveExerciseData(exerciseList)
        }

    }

    private fun initRepo(){
        dataRepo = DataRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }
}