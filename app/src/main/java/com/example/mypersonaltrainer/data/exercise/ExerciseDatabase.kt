package com.example.mypersonaltrainer.data.exercise

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Database(entities = [ExerciseEntity::class], version = 1, exportSchema = false)
abstract class ExerciseDatabase : RoomDatabase() {
    abstract fun exerciseDao(): ExerciseDao
}