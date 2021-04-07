package com.example.myfitnessroutine

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myfitnessroutine.data.Exercise

class RoutineRecyclerAdapter(private var exercises: MutableList<Exercise>) : RecyclerView.Adapter<RoutineRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = exercises.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoutineRecyclerAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.exercise_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = exercises[position]
        holder.nameText.text = currentItem.name
        holder.timeText.text = currentItem.time.toString() + " Seconds"
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.txtExerciseName)
        val timeText = itemView.findViewById<TextView>(R.id.txtExerciseTime)
    }

    fun setItems(exercise: MutableList<Exercise>) {
        this.exercises = exercise
    }
}