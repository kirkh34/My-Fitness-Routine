package com.example.myfitnessroutine

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise
import com.example.myfitnessroutine.data.Routine

class ExerciseRecyclerAdapter(
    val context: Context,
    val exercises: List<Exercise>,
    val itemListener: ExerciseItemListener

) : RecyclerView.Adapter<ExerciseRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = exercises.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.exercise_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise = exercises[position]
        with(holder){
            nameText?.let {
                it.text = exercise.name
                it.contentDescription = exercise.name
            }
            timeText?.let {
                it.text = exercise.time.toString() + " Seconds"
                it.contentDescription = exercise.time.toString() + " Seconds"
            }

            holder.itemView.setOnClickListener{
                itemListener.onExerciseItemClick(exercise)
            }

        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.txtExerciseName)
        val timeText = itemView.findViewById<TextView>(R.id.txtExerciseTime)
    }

    interface ExerciseItemListener {
        fun onExerciseItemClick(exercise: Exercise)
    }


}