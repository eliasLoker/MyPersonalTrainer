package com.example.mypersonaltrainer.createexercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseViewModel
import com.example.mypersonaltrainer.createexercise.viewmodel.CreateExerciseViewModelImpl
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
        binding!!.viewModel = createExerciseViewModel
        return binding!!.root
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