package com.example.mypersonaltrainer.programlist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.programlist.viewmodel.ProgramListViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by Alexandr Mikhalev on 11.06.2019.
 *
 * @author Alexandr Mikhalev
 */
class ProgramListFragment : Fragment() {

    @Inject
    lateinit var programListViewModel: ProgramListViewModel

    private var binding : com.example.mypersonaltrainer.databinding.FragmentProgramListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_program_list, container, false)
        binding!!.viewModel = programListViewModel

        return binding!!.root
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
        TODO()
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