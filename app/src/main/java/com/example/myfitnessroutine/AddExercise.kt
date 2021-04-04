package com.example.myfitnessroutine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise

class AddExercise : AppCompatActivity(),
    ExerciseRecyclerAdapter.ExerciseItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)

        recyclerView = findViewById(R.id.recyclerViewExercises)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.exerciseData.observe(this, Observer {
            val adapter = ExerciseRecyclerAdapter(this, it,this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
//            for (routine in it){
//                Log.i("loopList",
//                    "${routine.routineName} : ${routine.time}"
//                )
//            }
        })
    }


    override fun onExerciseItemClick(exercise: Exercise) {
        Log.i("click","Exercise Name: ${exercise.name}")
        //val intent = Intent(this, ViewRoutine::class.java)
        //intent.putExtra("routineExercises", exercise);
        //startActivity(intent)
    }
}