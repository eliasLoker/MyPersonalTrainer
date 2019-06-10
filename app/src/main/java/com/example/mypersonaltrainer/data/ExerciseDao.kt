package com.example.mypersonaltrainer.data

import androidx.room.Dao
import androidx.room.Insert
import io.reactivex.Completable

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Dao
interface ExerciseDao {

    @Insert
    fun insert(exerciseEntity: ExerciseEntity): Completable
}