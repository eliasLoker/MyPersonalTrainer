package com.example.mypersonaltrainer.createprogram.inject

import com.example.mypersonaltrainer.createprogram.CreateProgramFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@CreateProgramScope
@Subcomponent(modules = [CreateProgramModule::class])
interface CreateProgramComponent {

    fun inject(createProgramFragment: CreateProgramFragment)
}