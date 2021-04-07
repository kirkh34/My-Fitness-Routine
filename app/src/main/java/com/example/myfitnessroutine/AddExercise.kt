package com.example.myfitnessroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository

class AddExercise : AppCompatActivity(),
    ExerciseRecyclerAdapter.ExerciseItemListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : ExerciseRecyclerAdapter
    private lateinit var dataRepo : RoutineRepository
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_exercise)
        initRepo()

        recyclerView = findViewById(R.id.recyclerViewExercises)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = ExerciseRecyclerAdapter(this, exerciseList, this)
        recyclerView.adapter = adapter
    }


    override fun onExerciseItemClick(exercise: Exercise) {

        val routineID = intent.getStringExtra("routineID").toString()

        routineList.forEach{
            if(it.id == routineID){
                val exercises = it.exercises
                exercises.add(exercise.id)
            }
        }
        dataRepo.saveRoutineData(routineList)
    }

    private fun initRepo(){
        dataRepo = RoutineRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }
}