package com.example.mypersonaltrainer.exerciseslist

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
import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.data.exercise.ExerciseEntity
import com.example.mypersonaltrainer.exerciseslist.viewmodel.ExercisesListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 10.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ExercisesListFragment : Fragment(), OnItemButtonsClickListener, OnClickSettingsDialogButtonsListener,
    OnClickDeleteDialogButtonsListener {

    @Inject
    lateinit var exercisesListViewModel: ExercisesListViewModel

    private var binding: com.example.mypersonaltrainer.databinding.FragmentExercisesListBinding? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var exercisesListAdapter: ExercisesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercises_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.show()
        binding!!.viewModel = exercisesListViewModel
        init()

        recyclerView = binding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        exercisesListAdapter = ExercisesListAdapter()
        recyclerView.adapter = exercisesListAdapter

        exercisesListAdapter.setOnSettingsClickListener(this)
        return binding!!.root
    }

    private fun init() {
        exercisesListViewModel.updateListEvent.observe(this, Observer { setList(it.list) })
        exercisesListViewModel.showEditDialogEvent.observe(
            this,
            Observer {
                showEditDialog(
                    it.exerciseEntity.exerciseName,
                    it.exerciseEntity.numberOfRepeat.toString(),
                    it.exerciseEntity.numberOfRepetitions.toString(),
                    it.exerciseEntity.timeOfRest.toString()
                )
            })
        exercisesListViewModel.showDeleteDialogEvent.observe(this, Observer {
            showDeleteDialog(it.exerciseTitle)
        })
    }

    private fun setList(exerciseList: List<ExerciseEntity>) {
        val diffUtilExercisesList = DiffUtilExercisesList(exercisesListAdapter.data, exerciseList)
        val diffResult = DiffUtil.calculateDiff(diffUtilExercisesList)
        exercisesListAdapter.setList(exerciseList)
        diffResult.dispatchUpdatesTo(exercisesListAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exercises_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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

    private fun showEditDialog(title: String, numberOfRepeat: String, numberOfRepetitions: String, timeOfRest: String) {
        val editExerciseDialog =
            EditExerciseDialog().newInstance(title, numberOfRepeat, numberOfRepetitions, timeOfRest)
        editExerciseDialog.show(childFragmentManager, "TAG")

    }

    private fun showDeleteDialog(title: String) {
        val deleteExerciseDialog = DeleteExerciseDialog().newInstance(title)
        deleteExerciseDialog.show(childFragmentManager, "TAG2")
    }

    override fun onSettingsClicked(exerciseEntity: ExerciseEntity) {
        exercisesListViewModel.onSettingsClickedCallback(exerciseEntity)
    }

    override fun onBasketClicked(exerciseEntity: ExerciseEntity) {
        exercisesListViewModel.onBasketClickedCallback(exerciseEntity)
    }

    override fun onButtonSavedClicked(
        title: String,
        numberOfRepeat: String,
        numberOfRepetitions: String,
        timeOfRest: String
    ) {
        exercisesListViewModel.onButtonSavedClickedCallback(title, numberOfRepeat, numberOfRepetitions, timeOfRest)
    }

    override fun onConfirmDeleteDialogClicked() {
        exercisesListViewModel.onConfirmDeleteDialogClickedCallback()
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