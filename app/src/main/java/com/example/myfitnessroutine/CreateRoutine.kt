package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.DataRepository
import com.example.myfitnessroutine.data.uniqueID

class CreateRoutine : AppCompatActivity() {
    private lateinit var dataRepo: DataRepository
    private lateinit var routineList: MutableList<Routine>
    private lateinit var exerciseList: MutableList<Exercise>
    private lateinit var routineName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        initRepo()


        val btnCompleteRoutine = findViewById<Button>(R.id.completeRoutine)
        btnCompleteRoutine.setOnClickListener {

            val unique = uniqueID
            routineName = findViewById(R.id.editTextRoutineName)
            val routine = Routine(unique, routineName.text.toString(), arrayListOf())

            routineList.add(routineList.size, routine)

            dataRepo.saveRoutineData((routineList))

            val intent = Intent(this, ViewRoutine::class.java)
            intent.putExtra("routineID", unique);
            startActivity(intent)
        }
    }

    private fun initRepo() {
        dataRepo = DataRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }
}