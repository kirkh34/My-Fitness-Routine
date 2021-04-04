package com.example.myfitnessroutine

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.RoutineRepository


class MainActivity : AppCompatActivity(),
MainRecyclerAdapter.RoutineItemListener{

    private lateinit var viewModel: MainViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var exData : List<Exercise> = listOf()


        //Log.i("exData", exData.toString())
        recyclerView = findViewById(R.id.recyclerView)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.exerciseData.observe(this, Observer {
            exData = it
        })


        viewModel.routineData.observe(this, Observer {
            val adapter = MainRecyclerAdapter(this, it, exData, this)
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
//            for (routine in it){
//                Log.i("loopList",
//                    "${routine.routineName} : ${routine.time}"
//                )
//            }
        })



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
       // Log.i("click","Routine Name: ${routine.name}")
        val intent = Intent(this, ViewRoutine::class.java)
        intent.putExtra("routineExercises", routine);
        startActivity(intent)
    }

}
