package com.example.mypersonaltrainer.createprogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramViewModel
import com.example.mypersonaltrainer.data.ExerciseEntity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramFragment : Fragment() {

    @Inject
    lateinit var createProgramViewModel: CreateProgramViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentCreateProgramBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var createProgramAdapter: CreateProgramAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_program, container, false)
        binding!!.viewModel = createProgramViewModel
        init()

        recyclerView = binding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        createProgramAdapter = CreateProgramAdapter()
        recyclerView.adapter = createProgramAdapter

        return binding!!.root
    }

    private fun init() {
        createProgramViewModel.updateListEvent.observe(this, Observer { setList(it.list) })
    }

    private fun setList(exerciseList: List<ExerciseEntity>) {
        val diffUtilCreateProgram = DiffUtilCreateProgram(createProgramAdapter.data, exerciseList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCreateProgram)
        createProgramAdapter.setList(exerciseList)
        diffResult.dispatchUpdatesTo(createProgramAdapter)
    }

    companion object {
        fun newInstance(): CreateProgramFragment {
            val args = Bundle()
            val fragment = CreateProgramFragment()
            fragment.arguments = args
            return fragment
        }
    }
}