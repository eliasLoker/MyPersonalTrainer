package com.example.mypersonaltrainer.app.inject

import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.createexercise.inject.CreateExerciseModule
import com.example.mypersonaltrainer.createexercise.inject.CreateExerciseScope
import com.example.mypersonaltrainer.createprogram.CreateProgramFragment
import com.example.mypersonaltrainer.createprogram.inject.CreateProgramModule
import com.example.mypersonaltrainer.createprogram.inject.CreateProgramScope
import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import com.example.mypersonaltrainer.exerciseslist.inject.ExercisesListModule
import com.example.mypersonaltrainer.exerciseslist.inject.ExercisesListScope
import com.example.mypersonaltrainer.programlist.ProgramListFragment
import com.example.mypersonaltrainer.programlist.inject.ProgramListModule
import com.example.mypersonaltrainer.programlist.inject.ProgramListScope
import com.example.mypersonaltrainer.training.TrainingFragment
import com.example.mypersonaltrainer.training.inject.TrainingModule
import com.example.mypersonaltrainer.training.inject.TrainingScope
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

    @ContributesAndroidInjector(modules = [CreateProgramModule::class])
    @CreateProgramScope
    fun contributeCreateProgramFragment() : CreateProgramFragment

    @ContributesAndroidInjector(modules = [TrainingModule::class])
    @TrainingScope
    fun contributeTrainingFragment() : TrainingFragment
}