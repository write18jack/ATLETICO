package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF532Binding
import com.example.atletico.databinding.FragmentF541Binding

class F541Fragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels{
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }
    private var binding: FragmentF541Binding? = null
    lateinit var item: EntityX

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentF541Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            fragment541 = this@F541Fragment
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineupViewModel.renewalFormation(1,"5-4-1")
        Log.d("f541" , "$lineupViewModel.")
        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu)

        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.formation -> {
                    findNavController().navigate(R.id.action_f541Fragment_to_forDialog)
                    true
                }
                R.id.players -> {
                    findNavController().navigate(R.id.action_f532Fragment_to_playersFragment)
                    true
                }
                else -> false
            }
        }
    }
    fun goToPlayerList(position: Int){
        lineupViewModel.setPositionId(position)

        val action = F541FragmentDirections.actionF541FragmentToPlayersFragment(
            position, 541
        )
        this.findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}