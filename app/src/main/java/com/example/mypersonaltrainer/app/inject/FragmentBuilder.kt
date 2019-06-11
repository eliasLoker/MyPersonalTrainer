package com.example.mypersonaltrainer.app.inject

import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.createexercise.inject.CreateExerciseModule
import com.example.mypersonaltrainer.createexercise.inject.CreateExerciseScope
import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import com.example.mypersonaltrainer.exerciseslist.inject.ExercisesListModule
import com.example.mypersonaltrainer.exerciseslist.inject.ExercisesListScope
import com.example.mypersonaltrainer.programlist.ProgramListFragment
import com.example.mypersonaltrainer.programlist.inject.ProgramListModule
import com.example.mypersonaltrainer.programlist.inject.ProgramListScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@Module
interface FragmentBuilder {

    @ContributesAndroidInjector(modules = [CreateExerciseModule::class])
    @CreateExerciseScope
    fun contributeCreateExerciseFragment(): CreateExerciseFragment

    @ContributesAndroidInjector(modules = [ExercisesListModule::class])
    @ExercisesListScope
    fun contributeExercisesListFragment(): ExercisesListFragment

    @ContributesAndroidInjector(modules = [ProgramListModule::class])
    @ProgramListScope
    fun contributeProgramListFragment(): ProgramListFragment
}