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
    private val lineupviewModel: LineupViewModel by activityViewModels()
    private var binding: FragmentF541Binding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentF541Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupviewModel
            fragment541 = this@F541Fragment
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu)

        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.formation -> {
                    Log.d("Tag", "LineupF: ${it.itemId}")
                    val dialog = ForDialog(
                        {selectedFormation(it)}
                    )
                    dialog.show(parentFragmentManager, "formation_dialog")
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
        setFragmentResult("REQUEST_KEY", bundleOf("KEY" to position, "KEY2" to 541))
        findNavController().navigate(R.id.action_f541Fragment_to_playersFragment)
    }

    private fun selectedFormation(item:String){
        when(item){
            "3-1-4-2"->{findNavController().navigate(R.id.action_f541Fragment_to_f3142Fragment)}
            "4-4-2"->{findNavController().navigate(R.id.action_f541Fragment_to_f442Fragment)}
            "5-3-2"->{findNavController().navigate(R.id.action_f541Fragment_to_f532Fragment)}
            "5-4-1"->{Toast.makeText(context, "here!", Toast.LENGTH_LONG).show()}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}