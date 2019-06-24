package com.example.mypersonaltrainer.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createexercise.CreateExerciseFragment
import com.example.mypersonaltrainer.exerciseslist.ExercisesListFragment
import com.example.mypersonaltrainer.programlist.ProgramListFragment
import com.example.mypersonaltrainer.start.viewmodel.StartViewModel
import com.example.mypersonaltrainer.start.viewmodel.StartViewModelImpl
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar

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
        (activity as AppCompatActivity).supportActionBar?.hide()
        binding!!.viewModel = startViewModel
        init()
        //
        val constraintLayout: ConstraintLayout = binding!!.root.findViewById(R.id.bottom_sheet)
        val bottomSheetBehavior = BottomSheetBehavior.from(constraintLayout)
        val arrow: ImageView = binding!!.root.findViewById(R.id.arrow)
        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState) {
                    BottomSheetBehavior.STATE_COLLAPSED -> arrow.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_up, null))
                    BottomSheetBehavior.STATE_SETTLING -> arrow.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_down, null))
                }

            }
        })
        //
        return binding!!.root
    }

    private fun init() {
        startViewModel.goToCreateExerciseEvent.observe(this, Observer {
            goToCreateExerciseFragment()
        })

        startViewModel.goToMyTrainingsEvent.observe(this, Observer {
            goToMyTrainings()
        })

        startViewModel.showSnackbarEvent.observe(this, Observer { showSnackBar() })
    }

    private fun goToCreateExerciseFragment() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, ExercisesListFragment.newInstance())
            .commit()
    }

    private fun goToMyTrainings() {
        activity!!.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_activity_container, ProgramListFragment.newInstance())
            .commit()
    }

    private fun showSnackBar() {
        //Snackbar.make(binding!!.fab, resources.getString(R.string.snackbar_message), Snackbar.LENGTH_LONG).show()
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