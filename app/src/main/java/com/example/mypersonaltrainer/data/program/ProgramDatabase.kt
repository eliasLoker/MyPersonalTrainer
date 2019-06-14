package com.example.mypersonaltrainer.data.program

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Alexandr Mikhalev on 14.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Database(entities = [ProgramEntity::class], version = 1, exportSchema = false)
abstract class ProgramDatabase : RoomDatabase(){
    abstract fun ProgramDao() : ProgramDao
}