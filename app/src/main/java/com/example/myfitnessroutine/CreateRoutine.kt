package com.example.myfitnessroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository
import com.example.myfitnessroutine.data.uniqueID

class CreateRoutine : AppCompatActivity() {
    private lateinit var dataRepo : RoutineRepository
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        initRepo()

        val routine = Routine(uniqueID, "My workout", arrayListOf())

        routineList.add(routineList.size,routine)

        val btnCompleteRoutine = findViewById<Button>(R.id.completeRoutine)
        btnCompleteRoutine.setOnClickListener{
            dataRepo.saveRoutineData((routineList))
        }
    }

    private fun initRepo(){
        dataRepo = RoutineRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }
}