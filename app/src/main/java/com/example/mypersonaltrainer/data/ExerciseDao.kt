package com.example.mypersonaltrainer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Dao
interface ExerciseDao {

    @Query("SELECT * FROM ExerciseEntity")
    fun getAll(): Single<List<ExerciseEntity>>

    @Insert
    fun insert(exerciseEntity: ExerciseEntity): Completable
}