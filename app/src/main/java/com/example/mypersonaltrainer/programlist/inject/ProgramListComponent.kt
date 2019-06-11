package com.example.mypersonaltrainer.programlist.inject

import com.example.mypersonaltrainer.programlist.ProgramListFragment
import dagger.Subcomponent

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
@ProgramListScope
@Subcomponent(modules = [ProgramListModule::class])
interface ProgramListComponent {

    fun inject(programListFragment: ProgramListFragment)
}