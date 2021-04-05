package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository

class ViewRoutine : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataRepo : RoutineRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_routine)
        dataRepo = RoutineRepository(application)

        val routine = intent.getSerializableExtra("routineExercises") as? Routine
        var routineID = routine?.id
        val routineExIds = routine?.exercises
        Log.i("routineExIds","Result: ${routineExIds}")
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var list = routineExIds?.let { viewModel.buildExList(it) }
        Log.i("list","Result: ${list}")

        recyclerView = findViewById(R.id.recyclerViewRoutine)
        recyclerView.adapter = list?.let { RoutineRecyclerAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        //old stuff
        //val name = getIntent().getStringExtra("name")
        Log.i("getIntent","Result: ${routine?.name}")

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

        recyclerView = findViewById(R.id.recyclerViewRoutine)
        val dataRepo = RoutineRepository(application)

        val routine = intent.getSerializableExtra("routineExercises") as? Routine
        var routineID = routine?.id
        val routineExIds = routine?.exercises
        var list = routineExIds?.let { viewModel.buildExList(it) }
        var exercises = dataRepo.exerciseData
        var routines = dataRepo.routineData
        recyclerView.adapter?.notifyDataSetChanged()
        recyclerView.adapter = list?.let { RoutineRecyclerAdapter(it) }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }
}