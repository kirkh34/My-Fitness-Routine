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

class ViewRoutine : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_routine)

        val routine = intent.getSerializableExtra("routineExercises") as? Routine
        val routineExIds = routine?.exercises
        var exIds : List<String> = listOf("a5nbo03","b7al034")
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
            startActivity(intent)
        }
    }
}