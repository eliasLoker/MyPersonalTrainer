package com.example.mypersonaltrainer.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.start.viewmodel.StartViewModel
import com.example.mypersonaltrainer.start.viewmodel.StartViewModelImpl

/**
 * Created by Alexandr Mikhalev on 09.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class StartFragment : Fragment() {

    private lateinit var startViewModel: StartViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentStartBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startViewModel = ViewModelProviders.of(this).get(StartViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding!!.viewModel = startViewModel
        init()
        return binding!!.root
    }

    private fun init() {
        startViewModel.goToCreateExerciseEvent.observe(this, Observer {
            goToCreateExerciseFragment()
        })
    }

    private fun goToCreateExerciseFragment() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, CreateExerciseFragment.newInstance())
            .commit()
    }

    companion object {
        fun newInstance(): StartFragment {
            val args = Bundle()
            val fragment = StartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}