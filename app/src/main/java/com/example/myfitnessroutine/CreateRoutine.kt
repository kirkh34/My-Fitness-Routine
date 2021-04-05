package com.example.myfitnessroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.uniqueID

class CreateRoutine : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val routineList = viewModel.routineData
        val exercise = Exercise("a83lshd8", "Jumping Jacks", 120)
        val exercises = arrayListOf<String>()
        val routine = Routine(uniqueID, "My workout", exercises)
        routineList.add(routineList.size,routine)

        val btnCompleteRoutine = findViewById<Button>(R.id.completeRoutine)
        btnCompleteRoutine.setOnClickListener{
            //Log.i("create", "exercise created: ${.name}")
            viewModel.updateRoutineData(routineList)
        }
    }
}