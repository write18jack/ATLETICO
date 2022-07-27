package com.example.atletico.ui.lineup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF3142Binding

class F3142Fragment : Fragment() {

    private val lineupViewModel: LineupViewModel by activityViewModels()
    private var binding: FragmentF3142Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentF3142Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            fragment3142 = this@F3142Fragment
        }
        return fragmentBinding.root
    }

    fun goToPlayerList(position:Int){
        setFragmentResult("REQUEST_KEY", bundleOf("KEY" to position))
        findNavController().navigate(R.id.action_f3142Fragment_to_playersFragment)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}