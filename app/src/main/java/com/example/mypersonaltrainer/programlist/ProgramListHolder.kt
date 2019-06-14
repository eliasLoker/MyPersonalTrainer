package com.example.mypersonaltrainer.programlist

import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.data.program.ProgramEntity

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListHolder(var programListItemBinding: com.example.mypersonaltrainer.databinding.ProgramListItemBinding) :
    RecyclerView.ViewHolder(programListItemBinding.root) {

    fun bind(programEntity: ProgramEntity) {
        programListItemBinding.program = programEntity
        programListItemBinding.executePendingBindings()
    }
}