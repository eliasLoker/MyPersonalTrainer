package com.example.mypersonaltrainer.data.exercise

import androidx.room.*
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

    @Query("SELECT * FROM ExerciseEntity WHERE id = :id")
    fun getById(id: Long): Single<ExerciseEntity>

    @Insert
    fun insert(exerciseEntity: ExerciseEntity): Completable

    @Update
    fun update(exerciseEntity: ExerciseEntity): Completable

    @Delete
    fun delete(exerciseEntity: ExerciseEntity): Completable
}