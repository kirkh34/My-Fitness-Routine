package com.example.myfitnessroutine

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.DataRepository


class MainActivity : AppCompatActivity(),
MainRecyclerAdapter.RoutineItemListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataRepo : DataRepository
    private lateinit var adapter : MainRecyclerAdapter
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRepo()

        //clear cache
        applicationContext.cacheDir.deleteRecursively()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MainRecyclerAdapter(this, routineList, exerciseList, this)
        recyclerView.adapter = adapter

       initButtons()

    }

    override fun onResume() {
        super.onResume()
        initRepo()

        adapter.setItems(routineList, exerciseList )
        adapter.notifyDataSetChanged()
    }

    override fun onRoutineItemClick(routine: Routine) {
        val intent = Intent(this, ViewRoutine::class.java)
        intent.putExtra("routineID", routine.id);
        startActivity(intent)
    }

    private fun initRepo(){
        dataRepo = DataRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
    }

    private fun initButtons(){
        val btnCreateRoutine = findViewById<Button>(R.id.createRoutine)
        btnCreateRoutine.setOnClickListener{
            val intent = Intent(this, CreateRoutine::class.java)
            startActivity(intent)
        }
        val btnCreateExercise = findViewById<Button>(R.id.createExercise)
        btnCreateExercise.setOnClickListener{
            val intent = Intent(this, CreateExercise::class.java)
            startActivity(intent)
        }
        val btnHelp = findViewById<Button>(R.id.help)
        btnHelp.setOnClickListener{
            val intent = Intent(this, Help::class.java)
            startActivity(intent)
        }
    }

}
