package com.example.mypersonaltrainer.training.inject

import com.example.mypersonaltrainer.training.TrainingFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@TrainingScope
@Subcomponent(modules = [TrainingModule::class])
interface TrainingComponent {

    fun inject(trainingFragment: TrainingFragment)
}