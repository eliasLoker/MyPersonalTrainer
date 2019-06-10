package com.example.mypersonaltrainer.createexercise.inject

import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@CreateExerciseScope
@Subcomponent(modules = [CreateExerciseModule::class])
interface CreateExerciseComponent {

    fun inject(createExerciseFragment: CreateExerciseFragment)
}