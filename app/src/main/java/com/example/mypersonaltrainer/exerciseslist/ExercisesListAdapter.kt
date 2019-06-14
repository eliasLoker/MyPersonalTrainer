package com.example.mypersonaltrainer.exerciseslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListAdapter : RecyclerView.Adapter<ExercisesListHolder>() {

    var data: List<ExerciseEntity> = ArrayList()
    lateinit var listener: OnItemButtonsClickListener

    fun setList(list: List<ExerciseEntity>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: com.example.mypersonaltrainer.databinding.ExerciseListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.exercise_list_item, parent, false)
        return ExercisesListHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ExercisesListHolder, position: Int) {
        holder.bind(data[position])
        holder.exerciseListItemBinding.editView.setOnClickListener {
            listener.onSettingsClicked(holder.exerciseListItemBinding.exercise!!)
        }
        holder.exerciseListItemBinding.deleteView.setOnClickListener {
            listener.onBasketClicked(holder.exerciseListItemBinding.exercise!!)
        }
    }

    fun setOnSettingsClickListener(onItemButtonsClickListener: OnItemButtonsClickListener) {
        listener = onItemButtonsClickListener
    }
}