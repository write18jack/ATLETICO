package com.example.atletico.ui.lineup

import android.os.Bundle
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
import com.example.atletico.databinding.FragmentF3142Binding

class F3142Fragment : Fragment() {

    private val lineupViewModel: LineupViewModel by activityViewModels{
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }
    private var binding: FragmentF3142Binding? = null
    lateinit var item: EntityX

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lineupViewModel.renewalFormation(1, "3-1-4-2")
        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu)
        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.formation -> {
                    val dialog = ForDialog { selectedFormation(it) }
                    dialog.show(parentFragmentManager, "formation_dialog")
                    true
                }
                R.id.players -> {
                    findNavController().navigate(R.id.action_f442Fragment_to_playersFragment)
                    true
                }else->false
            }
        }
    }
    fun goToPlayerList(position:Int){
        lineupViewModel.setPositionId(position)
        val action = F3142FragmentDirections.actionF3142FragmentToPlayersFragment(
            position, 3142
        )
        this.findNavController().navigate(action)
    }
    private fun selectedFormation(item:String){
        when(item){
            "3-1-4-2"->{Toast.makeText(context, "here!", Toast.LENGTH_LONG).show()}
            "4-4-2"->{findNavController().navigate(R.id.action_f3142Fragment_to_f442Fragment)}
            "5-3-2"->{findNavController().navigate(R.id.action_f3142Fragment_to_f532Fragment)}
            "5-4-1"->{findNavController().navigate(R.id.action_f3142Fragment_to_f541Fragment)}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}