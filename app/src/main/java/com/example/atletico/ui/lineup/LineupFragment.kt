package com.example.atletico.ui.lineup

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.ActivityMainBinding
import com.example.atletico.databinding.FragmentLineUpBinding
import kotlinx.coroutines.launch

class LineupFragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels {
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }

    private var binding: FragmentLineUpBinding? = null
    private lateinit var bindingAct: ActivityMainBinding
    lateinit var item: EntityX

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLineUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLineup()
        setFormation()
        //parentFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun setLineup() {
        lifecycle.coroutineScope.launch {
            lineupViewModel.allItems().observe(viewLifecycleOwner) {
                for (i in it) {
                    lineupViewModel.mapPositionPlayer[i.itemPosition] = i.itemPlayer
                    lineupViewModel.setPositionId(i.itemPosition)
                    lineupViewModel.setPlayerId(i.itemPlayer)
                    lineupViewModel.select()
                }
            }
        }
    }

    private fun setFormation() {
        lifecycle.coroutineScope.launch {
            lineupViewModel.formationItem().observe(viewLifecycleOwner) {
                if (it == null) {
                    lineupViewModel.addFormation(1, "4-4-2")
                    selectedFormation("4-4-2")
                } else {
                    selectedFormation(it.itemLastFormation)
                }
            }
        }
    }

    private fun selectedFormation(item: String) {
        when (item) {
            "3-1-4-2" -> {
                findNavController().navigate(R.id.action_lineupFragment_to_f3142Fragment)
            }
            "4-4-2" -> {
                findNavController().navigate(R.id.action_lineupFragment_to_f442Fragment)
            }
            "5-3-2" -> {
                findNavController().navigate(R.id.action_lineupFragment_to_f532Fragment)
            }
            "5-4-1" -> {
                findNavController().navigate(R.id.action_lineupFragment_to_f541Fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}