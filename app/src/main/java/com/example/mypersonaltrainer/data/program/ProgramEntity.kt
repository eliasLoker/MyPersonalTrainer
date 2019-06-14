package com.example.mypersonaltrainer.data.program

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Entity
@TypeConverters(ProgramConverter::class)
class ProgramEntity(
    var name: String,
    var list: List<Long>,
    var timeOfRest: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}