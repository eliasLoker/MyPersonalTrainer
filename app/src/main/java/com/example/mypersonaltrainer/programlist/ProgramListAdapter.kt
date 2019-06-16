package com.example.mypersonaltrainer.programlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.data.program.ProgramEntity

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListAdapter : RecyclerView.Adapter<ProgramListHolder>() {

    var data: List<ProgramEntity> = ArrayList()

    lateinit var listener: OnClickProgramListItemListener

    fun setList(list: List<ProgramEntity>) {
        data = list
        notifyDataSetChanged()
    }

    fun setonClickProgramListItemListener (onClickProgramListItemListener: OnClickProgramListItemListener) {
        listener = onClickProgramListItemListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramListHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: com.example.mypersonaltrainer.databinding.ProgramListItemBinding =
            DataBindingUtil.inflate(inflater, R.layout.program_list_item, parent, false)
        return ProgramListHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ProgramListHolder, position: Int) {
        holder.bind(data[position])
        holder.programListItemBinding.startImageView.setOnClickListener {
            listener.onClickStartButton(holder.programListItemBinding.program!!.id!!)
        }
    }
}