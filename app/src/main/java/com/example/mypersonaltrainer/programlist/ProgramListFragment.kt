package com.example.mypersonaltrainer.programlist

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createprogram.CreateProgramFragment
import com.example.mypersonaltrainer.data.program.ProgramEntity
import com.example.mypersonaltrainer.programlist.viewmodel.ProgramListViewModel
import com.example.mypersonaltrainer.training.TrainingFragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListFragment : Fragment(), OnClickProgramListItemListener, OnClickEditDialogButtonsListener,
    OnClickDeleteDialogButtonsListener {

    @Inject
    lateinit var programListViewModel: ProgramListViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentProgramListBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var programListAdapter: ProgramListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_program_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.show()
        binding!!.viewModel = programListViewModel
        init()

        //
        recyclerView = binding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        programListAdapter = ProgramListAdapter()
        recyclerView.adapter = programListAdapter
        programListAdapter.setonClickProgramListItemListener(this)
        //
        return binding!!.root
    }

    private fun init() {
        programListViewModel.updateListEvent.observe(this, Observer { setList(it.list) })
        programListViewModel.goToTrainingEvent.observe(this, Observer { goToTraining(it.id) })
        programListViewModel.showEditProgramDialogEvent.observe(
            this,
            Observer { showEditDialog(it.title, it.timeOfRest) })
        programListViewModel.showDeleteDialogEvent.observe(this, Observer { showDeleteDialog(it.exerciseTitle) })
    }

    private fun setList(programList: List<ProgramEntity>) {
        val diffUtilProgramList = DiffUtilProgramList(programListAdapter.data, programList)
        val diffResult = DiffUtil.calculateDiff(diffUtilProgramList)
        programListAdapter.setList(programList)
        diffResult.dispatchUpdatesTo(programListAdapter)
    }

    private fun goToTraining(id: Long) {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, TrainingFragment.newInstance(id))
            .commit()
    }

    private fun showEditDialog(title: String, timeOfRest: String) {
        val editDialog = EditProgramDialog().newInstance(title, timeOfRest)
        editDialog.show(childFragmentManager, "TAAG")
    }

    private fun showDeleteDialog(title: String) {
        val deleteExerciseDialog = DeleteProgramDialog().newInstance(title)
        deleteExerciseDialog.show(childFragmentManager, "TAG2")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.programs_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.programs_menu_item -> goToCreateProgramFragment()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun goToCreateProgramFragment() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, CreateProgramFragment.newInstance())
            .commit()
    }

    override fun onClickStartButton(id: Long) {
        programListViewModel.onClickStartButtonCallback(id)
    }

    override fun onSettingsClicked(id: Long) {
        programListViewModel.onSettingsClickedCallback(id)
    }

    override fun onBasketClicked(id: Long) {
        programListViewModel.onBasketClickedCallback(id)
    }

    override fun onButtonSavedClicked(title: String, timeOfRest: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConfirmDeleteDialogClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun newInstance(): ProgramListFragment {
            val args = Bundle()
            val fragment = ProgramListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}