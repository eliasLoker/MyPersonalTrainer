package com.example.mypersonaltrainer.createprogram

import android.graphics.Color
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.core.view.MotionEventCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.data.ExerciseEntity
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramAdapter(private val onStartDragListener: OnStartDragListener) :
    RecyclerView.Adapter<CreateProgramHolder>(),
    ItemTouchHelperAdapter {

    val TAG  = "CreateProgramAdapter"

    var data = ArrayList<ExerciseEntity>().toMutableList()

    var map = HashMap<Int, Int>().toMutableMap()

    fun setList(list: List<ExerciseEntity>) {
        data = list.toMutableList()
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

        holder.exerciseListForProgramItemBinding.checkbox.setOnCheckedChangeListener { _, b ->
            if (b) {
                holder.itemView.setBackgroundColor(Color.LTGRAY)
                Log.d(TAG, "onBindViewHolder $position")
            }
        }
        /*
        holder.exerciseListForProgramItemBinding.nameTextView.setOnTouchListener{
                _, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            false
        }
        */

        holder.exerciseListForProgramItemBinding.linesImageView.setOnTouchListener { _, event ->
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                onStartDragListener.onStartDrag(holder)
            }
            false
        }

    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {

        Log.d(TAG, "onItemMove")
        Collections.swap(data, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }
}