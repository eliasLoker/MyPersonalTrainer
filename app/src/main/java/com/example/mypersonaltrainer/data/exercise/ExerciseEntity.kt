package com.example.mypersonaltrainer.data.exercise

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Entity
class ExerciseEntity(var exerciseName: String, var numberOfRepeat: Int, var numberOfRepetitions: Int, var timeOfRest: Int) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}