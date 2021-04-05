package com.example.myfitnessroutine

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository


class MainActivity : AppCompatActivity(),
MainRecyclerAdapter.RoutineItemListener{

    private lateinit var recyclerView: RecyclerView


     fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main,container,false)

//        recyclerView = view.findViewById(R.id.recyclerView)
//
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        viewModel.routineData.observe(this, Observer {
//            val adapter = MainRecyclerAdapter(this, it)
//            recyclerView.adapter = adapter
////            for (routine in it){
////                Log.i("loopList",
////                    "${routine.routineName} : ${routine.time}"
////                )
////            }
//        })


        return view
    }

    override fun onResume() {
        super.onResume()

        recyclerView = findViewById(R.id.recyclerView)
        val dataRepo = RoutineRepository(application)

        var exercises = dataRepo.exerciseData
        var routines = dataRepo.routineData

        val adapter = MainRecyclerAdapter(this, routines, exercises, this)
        adapter.notifyDataSetChanged()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        Log.i("log","onResume ran")
        Log.i("log","${routines}")


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //clear cache
        //applicationContext.cacheDir.deleteRecursively()

        val viewModel: MainViewModel by viewModels()
        recyclerView = findViewById(R.id.recyclerView)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val dataRepo = RoutineRepository(application)

        var exercises = dataRepo.exerciseData
        var routines = dataRepo.routineData

        val adapter = MainRecyclerAdapter(this, routines, exercises, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)


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
            //startActivity(intent)
            Log.i("log","${routines}")

        }

        /*
        val routine = Routine("First Workout",1000)
        Log.i("routineLogging", routine.toString())


        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val routines = arrayOf(
                "Routine 1", "Routine 2", "Routine 3",
                "Routine 4", "Routine 5"
        )


        // access the listView from xml file
        var mListView = findViewById<ListView>(R.id.routineList)
        arrayAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, routines)
        mListView.adapter = arrayAdapter

        mListView.setOnItemClickListener { parent, view, position, id ->
            val element =  mListView.getItemAtPosition(position) // The item that was clicked
            val intent = Intent(this, ViewRoutine::class.java)
            intent.putExtra("name", element.toString());
            startActivity(intent)
        }

         */

    }

    override fun onRoutineItemClick(routine: Routine) {
            Log.i("click","Routine Name: ${routine}")
        val intent = Intent(this, ViewRoutine::class.java)
        intent.putExtra("routineExercises", routine);
        startActivity(intent)
    }

}
