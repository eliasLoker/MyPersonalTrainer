package com.example.mypersonaltrainer.exerciseslist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListViewModel
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListViewModelImpl

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListFragment : Fragment() {

    private lateinit var exercisesListViewModel: ExercisesListViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentExercisesListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exercisesListViewModel = ViewModelProviders.of(this).get(ExercisesListViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercises_list, container, false)
        binding!!.viewModel = exercisesListViewModel
        return binding!!.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercises_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exercises_menu_item -> goToCreateExerciseFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToCreateExerciseFragment() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, CreateExerciseFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance(): ExercisesListFragment {
            val args = Bundle()
            val fragment = ExercisesListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}