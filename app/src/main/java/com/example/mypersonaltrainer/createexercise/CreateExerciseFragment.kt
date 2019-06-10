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

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateExerciseFragment : Fragment() {

    private lateinit var createExerciseViewModel: CreateExerciseViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentCreateExerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createExerciseViewModel = ViewModelProviders.of(this).get(CreateExerciseViewModelImpl::class.java)
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