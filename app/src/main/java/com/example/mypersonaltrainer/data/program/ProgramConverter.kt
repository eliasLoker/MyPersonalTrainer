package com.example.mypersonaltrainer.data.program

import androidx.room.TypeConverter
import android.R.array



/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramConverter {

    @TypeConverter
    fun fromProgram(list: List<Long>) : String {
        var gen : String = ""
        for (i in list) {
            gen += ",$i"
        }
        return gen
    }

    @TypeConverter
    fun toProgram(program: String) : List<Long> {
        var list = ArrayList<Long>()

        val array = program.split(",")

        for (s in array) {
            if (s.isNotEmpty()) {
                list.add(s.toLong())
            }
        }
        return list
    }
}