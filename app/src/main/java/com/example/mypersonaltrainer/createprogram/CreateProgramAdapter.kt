package com.example.mypersonaltrainer.createprogram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.data.ExerciseEntity

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramAdapter : RecyclerView.Adapter<CreateProgramHolder>() {

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
    }
}