package com.example.myfitnessroutine

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine
import com.example.myfitnessroutine.data.DataRepository
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlinx.android.synthetic.main.activity_view_routine.*

class ViewRoutine : AppCompatActivity() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var dataRepo : DataRepository
    private lateinit var adapter : RoutineRecyclerAdapter
    private lateinit var routineList : MutableList<Routine>
    private lateinit var exerciseList : MutableList<Exercise>

    private lateinit var routineID : String
    private lateinit var selectedRoutineExerciseList: MutableList<Exercise>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_routine)

        routineID = intent.getStringExtra("routineID").toString()

        initRepo()

        recyclerView = findViewById(R.id.recyclerViewRoutine)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        selectedRoutineExerciseList = dataRepo.buildRoutineExerciseList(routineID)

        adapter = RoutineRecyclerAdapter(selectedRoutineExerciseList)
        recyclerView.adapter = adapter

        var timeRemaining : Long

        var routineName = dataRepo.getRoutine(routineID)?.name

        routineNameText.text = routineName

        if(selectedRoutineExerciseList.isNotEmpty()) {
            addAnExercise.text = ""
        }


            fun startWorkout(exercise: Exercise) {
            exNameCountdown.text = exercise.name
            var exerciseTime = exercise.time * 1000
            //Log.i("timer", (exercise.time.toString()))
                object : CountDownTimer(exerciseTime.toLong(), 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeRemaining = millisUntilFinished/1000 + 1
                    timerCountdown.text = timeRemaining.toString()
                    //Log.i("timer", (millisUntilFinished/1000).toString())
                    var remain = 1 - ((millisUntilFinished - 1).toDouble() / exerciseTime.toDouble())
                    circularProgressBar.progress = remain.toFloat()
                    //Log.i("remain", remain.toString())

                }
                override fun onFinish() {
                    selectedRoutineExerciseList.removeAt(0)
                    adapter.setItems(selectedRoutineExerciseList)
                    adapter.notifyDataSetChanged()
                    if(selectedRoutineExerciseList.isNotEmpty()) {
                        var exercise = selectedRoutineExerciseList.get(0)
                        startWorkout(exercise)
                    } else {
                        timerCountdown.text = "You did it!"
                        exNameCountdown.text = ""
                        timerCountdown.textSize = 32f
                        circularProgressBar.progressBarColor = Color.GREEN
                        circularProgressBar.backgroundProgressBarColor = Color.GREEN
                    }
                }
            }.start()
        }

        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)
        circularProgressBar.apply {
            // Set Progress
            progress = 0f
            // or with animation
            //setProgressWithAnimation(65f, 10000) // =1s

            // Set Progress Max
            progressMax = 1f

            // Set ProgressBar Color
            progressBarColor = Color.GREEN

            progressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.LTGRAY

            backgroundProgressBarColorDirection = CircularProgressBar.GradientDirection.TOP_TO_BOTTOM

            // Set Width
            progressBarWidth = 21f // in DP
            backgroundProgressBarWidth = 21f // in DP

            // Other
            roundBorder = false
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

        circularProgressBar.onProgressChangeListener = { progress ->
            // Do something
        }

        circularProgressBar.onIndeterminateModeChangeListener = { isEnable ->
            // Do something
        }

        val btnStartRoutine = findViewById<Button>(R.id.startRoutine)
        btnStartRoutine.setOnClickListener{
            var exercise = selectedRoutineExerciseList.get(0)
            startWorkout(exercise)
        }

        val btnAddExercise = findViewById<Button>(R.id.addExercise)
        btnAddExercise.setOnClickListener{
            val intent = Intent(this, AddExercise::class.java)
            intent.putExtra("routineID", routineID);
            startActivity(intent)
        }

        val btnViewRoutineGoBack = findViewById<Button>(R.id.viewRoutineGoBack)
        btnViewRoutineGoBack.setOnClickListener{
            this.finish()
        }
    }

    override fun onResume() {
        super.onResume()
        initRepo()
        adapter.setItems(selectedRoutineExerciseList)
        adapter.notifyDataSetChanged()

        if(selectedRoutineExerciseList.isNotEmpty()) {
            addAnExercise.text = ""
        }
        Log.i("exercises", selectedRoutineExerciseList.toString())

    }

    private fun initRepo(){
        dataRepo = DataRepository(application)
        routineList = dataRepo.routineData
        exerciseList = dataRepo.exerciseData
        selectedRoutineExerciseList = dataRepo.buildRoutineExerciseList(routineID)
    }



}