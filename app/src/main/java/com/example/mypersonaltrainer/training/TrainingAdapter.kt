package com.example.mypersonaltrainer.training

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R

/**
 * Created by Alexandr Mikhalev on 17.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingAdapter : RecyclerView.Adapter<TrainingHolder>() {

    var data: List<Long> = ArrayList()

    var currentItemId: Int = 0

    var FIRST_TYPE = 0
    var SECOND_TYPE = 1

    fun setListAndType(list: List<Long>, currentId: Int) {
        data = list
        currentItemId = currentId
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            currentItemId -> FIRST_TYPE
            else -> SECOND_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.training_item, parent, false)
        val view = when (viewType) {
            FIRST_TYPE -> LayoutInflater.from(parent.context).inflate(R.layout.training_item_current, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.training_item, parent, false)
        }
        return TrainingHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TrainingHolder, position: Int) {
        holder.numberTextView.text = data[position].toString()
    }
}