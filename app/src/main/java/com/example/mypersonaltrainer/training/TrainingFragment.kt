package com.example.mypersonaltrainer.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var trainingAdapter: TrainingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
        val id = arguments!!.getLong(KEY)
        trainingFactory = TrainingFactory(trainingInteractor, id)
        trainingViewModel = ViewModelProviders.of(this, trainingFactory).get(TrainingViewModelImpl::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_training, container, false)
        binding!!.viewModel = trainingViewModel
        init()
        //
        recyclerView = binding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        trainingAdapter = TrainingAdapter()
        recyclerView.adapter = trainingAdapter

        //
        return binding!!.root
    }

    private fun init() {
        trainingViewModel.updateListEvent.observe(
            this,
            Observer { trainingAdapter.setListAndType(it.list, it.currentId) })

        trainingViewModel.trainingOffEvent.observe(this, Observer {showToast(it.msg)})
    }

    private fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
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