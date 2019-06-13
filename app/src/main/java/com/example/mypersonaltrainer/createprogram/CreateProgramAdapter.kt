package com.example.mypersonaltrainer.createprogram

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.data.ExerciseEntity
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramAdapter(val onStartDragListener: OnStartDragListener) : RecyclerView.Adapter<CreateProgramHolder>(),
    ItemTouchHelperAdapter {

    var data: List<ExerciseEntity> = ArrayList()

    fun setList(list: List<ExerciseEntity>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateProgramHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: com.example.mypersonaltrainer.databinding.ExerciseListForProgramItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.exercise_list_for_program_item, parent, false)
        return CreateProgramHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CreateProgramHolder, position: Int) {
        holder.bind(data[position])

        /*
        holder.itemView.setOnTouchListener { _, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            false
        }
        */
        holder.exerciseListForProgramItemBinding.nameTextView.setOnTouchListener{
                _, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            false
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(data, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        data.toMutableList().removeAt(position)
        notifyItemRemoved(position)
    }
}