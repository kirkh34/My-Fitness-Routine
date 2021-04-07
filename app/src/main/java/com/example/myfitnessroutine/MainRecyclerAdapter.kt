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

class MainRecyclerAdapter(
    val context: Context,
    var routines: MutableList<Routine>,
    var exercises: MutableList<Exercise>,
    val itemListener: MainActivity

) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun getItemCount() = routines.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val routine = routines[position]
        with(holder){
            nameText?.let {
                it.text = routine.name
                it.contentDescription = routine.name
            }
            val ex = routine.exercises

            var exList : String = ""
            var totalSecs : Int = 0
            ex.forEach { routineEx ->
                exList += " $routineEx"
                exercises.forEach{ exID ->
                    Log.i("exList", exID.id)
                    if (routineEx == exID.id){
                        totalSecs += exID.time
                    }
                }


            }
            var hours = totalSecs / 3600;
            var minutes = (totalSecs % 3600) / 60;
            var seconds = totalSecs % 60;
            var timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);

            timeText?.let {
                it.text = timeString
                    it.contentDescription = timeString
            }

            holder.itemView.setOnClickListener{
                itemListener.onRoutineItemClick(routine)
            }

        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText = itemView.findViewById<TextView>(R.id.txtRoutineName)
        val timeText = itemView.findViewById<TextView>(R.id.txtRoutineTime)
    }

    interface RoutineItemListener {
        fun onRoutineItemClick(routine: Routine)
    }

    fun setItems(routine: MutableList<Routine>, exercises: MutableList<Exercise>) {
        this.routines = routine
        this.exercises = exercises

    }

}