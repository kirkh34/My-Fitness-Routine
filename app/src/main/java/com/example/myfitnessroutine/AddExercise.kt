package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.LOG_TAG
import com.example.myfitnessroutine.data.RoutineRepository
import com.example.myfitnessroutine.data.uniqueID

class AddExercise : AppCompatActivity(),
    ExerciseRecyclerAdapter.ExerciseItemListener{


    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataRepo : RoutineRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        dataRepo = RoutineRepository(application)

        recyclerView = findViewById(R.id.recyclerViewExercises)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val exData = viewModel.exerciseData

        recyclerView.adapter = ExerciseRecyclerAdapter(this, exData, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)



    }


    override fun onExerciseItemClick(exercise: Exercise) {
        Log.i("click","Exercise Name: ${exercise.name}")
        //val intent = Intent(this, ViewRoutine::class.java)
        //intent.putExtra("routineExercises", exercise);
        //startActivity(intent)
        val exerciseList = dataRepo.exerciseData
        val routineList = dataRepo.routineData
        var routineID = intent.getStringExtra("routineID").toString()
        //val exercise = Exercise(uniqueID, "Jumping Jacks", 120)
        Log.i(LOG_TAG,routineList.toString())
        routineList.forEach{
            if(it.id == routineID){
                val exercises = it.exercises
                Log.i(LOG_TAG,exercises.size.toString())
                exercises.add(exercise.id)
            }
        }
        viewModel.updateRoutineData(routineList)
    }
}