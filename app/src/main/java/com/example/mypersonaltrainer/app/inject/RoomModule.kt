package com.example.mypersonaltrainer.app.inject

import android.content.Context
import androidx.room.Room
import com.example.mypersonaltrainer.data.exercise.ExerciseDatabase
import com.example.mypersonaltrainer.data.program.ProgramDatabase
import dagger.Module
import dagger.Provides

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
class RoomModule {

    @AppScope
    @Provides
    fun provideExerciseDatabase(context: Context) =
        Room.databaseBuilder(context, ExerciseDatabase::class.java, "myDB").build()

    @AppScope
    @Provides
    fun provideExerciseDao(exerciseDatabase: ExerciseDatabase) = exerciseDatabase.exerciseDao()

    @AppScope
    @Provides
    fun provideProgramDatabase(context: Context) =
        Room.databaseBuilder(context, ProgramDatabase::class.java, "ProgramDB").build()

    @AppScope
    @Provides
    fun provideProgramDao(programDatabase: ProgramDatabase) = programDatabase.ProgramDao()
}