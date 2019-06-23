package com.example.mypersonaltrainer.createexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createexercise.events.InputErrorEvent
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseViewModel
import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseFragment : Fragment() {

    @Inject
    lateinit var createExerciseViewModel: CreateExerciseViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentCreateExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_exercise, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding!!.viewModel = createExerciseViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        createExerciseViewModel.errorInputEvent.observe(this, Observer { setErrorMessages(it.errorType) })
        createExerciseViewModel.goToExerciseListEvent.observe(this, Observer { goToExerciseListEvent() })
    }

    private fun setErrorMessages(errorType: InputErrorEvent.ErrorType) {
        when (errorType) {
            InputErrorEvent.ErrorType.TITLE_ERROR
            -> binding!!.inputTitleEditText.error = resources.getString(R.string.error_title)
            InputErrorEvent.ErrorType.NUMBER_OF_REPEAT_ERROR
            -> binding!!.inputAmountEditText.error = resources.getString(R.string.error_number_of_repeat)
            InputErrorEvent.ErrorType.NUMBER_OF_REPETITIONS_ERROR
            -> binding!!.inputAmountRepetitionsEditText.error =
                resources.getString(R.string.error_number_of_repetitions)
            InputErrorEvent.ErrorType.TIME_OF_REST_ERROR
                    -> binding!!.inputTimeOfRestEditText.error = resources.getString(R.string.error_time_of_rest)
        }
    }

    private fun goToExerciseListEvent() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, ExercisesListFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance(): CreateExerciseFragment {
            val args = Bundle()
            val fragment = CreateExerciseFragment()
            fragment.arguments = args
            return fragment
        }
    }
}