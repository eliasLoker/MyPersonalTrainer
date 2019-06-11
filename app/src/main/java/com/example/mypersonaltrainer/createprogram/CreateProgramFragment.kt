package com.example.mypersonaltrainer.createprogram

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.mypersonaltrainer.R
import com.example.mypersonaltrainer.createprogram.viewmodel.CreateProgramViewModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_create_program, container, false)
        binding!!.viewModel = createProgramViewModel
        return binding!!.root
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