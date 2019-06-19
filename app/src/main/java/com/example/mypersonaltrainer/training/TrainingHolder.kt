package com.example.mypersonaltrainer.training

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R

/**
 * Created by Alexandr Mikhalev on 17.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingHolder(val view: View) : RecyclerView.ViewHolder(view) {

    internal var numberTextView: TextView = view.findViewById(R.id.number_text_view)
}