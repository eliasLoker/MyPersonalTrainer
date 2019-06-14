package com.example.mypersonaltrainer.createprogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramViewModel
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class CreateProgramFragment : Fragment(), OnStartDragListener, OnMoveListListener, OnCreateProgramDialogListener {

    @Inject
    lateinit var createProgramViewModel: CreateProgramViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentCreateProgramBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var createProgramAdapter: CreateProgramAdapter

    private lateinit var itemTouchHelper: ItemTouchHelper

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

        createProgramAdapter = CreateProgramAdapter(this)
        recyclerView.adapter = createProgramAdapter

        //
        val callback = SimpleItemTouchHelperCallback(createProgramAdapter)
        itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        createProgramAdapter.setListener(this)
        //
        return binding!!.root
    }

    private fun init() {
        createProgramViewModel.updateListEvent.observe(this, Observer { setList(it.list) })

        createProgramViewModel.showDialogEvent.observe(this, Observer { showDialog() })
    }

    private fun showDialog() {
        val createProgramDialog = CreateProgramDialog().newInstance()
        createProgramDialog.show(childFragmentManager, "TAGGG")
    }

    private fun setList(exerciseList: List<ExerciseEntity>) {
        val diffUtilCreateProgram = DiffUtilCreateProgram(createProgramAdapter.data, exerciseList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCreateProgram)
        createProgramAdapter.setList(exerciseList)
        diffResult.dispatchUpdatesTo(createProgramAdapter)
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        createProgramViewModel.onItemMoveCallback(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        createProgramViewModel.onItemDismissCallback(position)
    }

    override fun onClickSaveButton(programName: String, timeOfRest: String) {
        createProgramViewModel.onClickSaveButtonCallback(programName, timeOfRest)
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