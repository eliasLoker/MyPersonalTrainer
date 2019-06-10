package com.example.mypersonaltrainer.exerciseslist.inject

import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@ExercisesListScope
@Subcomponent(modules = [ExercisesListModule::class])
interface ExerciseListComponent {

    fun inject(exercisesListFragment: ExercisesListFragment)
}