package com.example.mypersonaltrainer.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.databinding.FragmentTrainingBinding
import com.example.mypersonaltrainer.training.interactor.TrainingInteractor
import com.example.mypersonaltrainer.training.viewmodel.TrainingFactory
import com.example.mypersonaltrainer.training.viewmodel.TrainingViewModel
import com.example.mypersonaltrainer.training.viewmodel.TrainingViewModelImpl
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 16.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class TrainingFragment : Fragment() {

    /*
    @Inject
    lateinit var trainingViewModel: TrainingViewModel
    */
    @Inject
    lateinit var trainingInteractor: TrainingInteractor

    lateinit var trainingViewModel: TrainingViewModel

    lateinit var trainingFactory: TrainingFactory

    private var binding: FragmentTrainingBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        val id = arguments!!.getLong(KEY)
        trainingFactory = TrainingFactory(trainingInteractor, id)
        trainingViewModel = ViewModelProviders.of(this, trainingFactory).get(TrainingViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_training, container, false)
        binding!!.viewModel = trainingViewModel
        return binding!!.root
    }

    companion object {
        private const val KEY = "TrainingFragmentKey"

        fun newInstance(id: Long): TrainingFragment {
            val args = Bundle()
            args.putLong(KEY, id)
            val fragment = TrainingFragment()
            fragment.arguments = args
            return fragment
        }
    }
}