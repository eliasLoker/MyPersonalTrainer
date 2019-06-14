package com.example.mypersonaltrainer.programlist

import android.os.Bundle
import android.view.*
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

    private lateinit var recyclerView: RecyclerView
    private lateinit var programListAdapter: ProgramListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_program_list, container, false)
        binding!!.viewModel = programListViewModel
        init()

        //
        recyclerView = binding!!.recyclerView
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        programListAdapter = ProgramListAdapter()
        recyclerView.adapter = programListAdapter

        //
        return binding!!.root
    }

    private fun init() {
        programListViewModel.updateListEvent.observe(this, Observer { setList(it.list) })
    }

    private fun setList(programList: List<ProgramEntity>) {
        val diffUtilProgramList = DiffUtilProgramList(programListAdapter.data, programList)
        val diffResult = DiffUtil.calculateDiff(diffUtilProgramList)
        programListAdapter.setList(programList)
        diffResult.dispatchUpdatesTo(programListAdapter)
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

    companion object {
        fun newInstance(): ProgramListFragment {
            val args = Bundle()
            val fragment = ProgramListFragment()
            fragment.arguments = args
            return fragment
        }
    }
}