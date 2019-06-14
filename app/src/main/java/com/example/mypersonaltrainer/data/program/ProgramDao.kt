package com.example.mypersonaltrainer.data.program

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Dao
interface ProgramDao {

    @Query("SELECT * FROM ProgramEntity")
    fun getAll(): Single<List<ProgramEntity>>

    @Insert
    fun insert(programEntity: ProgramEntity): Completable
}