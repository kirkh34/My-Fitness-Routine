package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository

class ViewRoutine : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dataRepo : RoutineRepository
    private lateinit var adapter : RoutineRecyclerAdapter
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>



    private lateinit var routine: Routine
    private lateinit var routineID: String
    private lateinit var routineExerciseIds: MutableList<String>
    private lateinit var routineExerciseList: MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_routine)

        initRepo()

        recyclerView = findViewById(R.id.recyclerViewRoutine)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)


        routine = intent.getSerializableExtra("routineExercises") as Routine
        routineID = routine.id
        routine = dataRepo.getRoutine(routineID)!!
        routineExerciseIds = routine.exercises
        routineExerciseList = dataRepo.buildExerciseList(routineExerciseIds)


        adapter = RoutineRecyclerAdapter(routineExerciseList)
        recyclerView.adapter = adapter


        val btnStartRoutine = findViewById<Button>(R.id.startRoutine)
        btnStartRoutine.setOnClickListener{
        }

        val btnAddExercise = findViewById<Button>(R.id.addExercise)
        btnAddExercise.setOnClickListener{
            val intent = Intent(this, AddExercise::class.java)
            intent.putExtra("routineID", routineID);
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        initRepo()

        routine = dataRepo.getRoutine(routineID)!!
        routineExerciseIds = routine.exercises
        routineExerciseList = dataRepo.buildExerciseList(routineExerciseIds)
        adapter.setItems(routineExerciseList)
        adapter.notifyDataSetChanged()

    }

    private fun initRepo(){
        dataRepo = RoutineRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }

}