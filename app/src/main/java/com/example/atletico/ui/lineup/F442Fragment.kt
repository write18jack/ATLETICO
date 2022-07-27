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
import com.example.atletico.databinding.FragmentF442Binding

class F442Fragment : Fragment() {

    private val lineupviewModel: LineupViewModel by activityViewModels()
    private var binding: FragmentF442Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentF442Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupviewModel
            fragment442 = this@F442Fragment
        }
        return fragmentBinding.root
    }

    fun goToPlayerList(position: Int){
        setFragmentResult("REQUEST_KEY", bundleOf("KEY" to position))
        findNavController().navigate(R.id.action_f442Fragment_to_playersFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}