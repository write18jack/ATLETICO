package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF442Binding
import kotlinx.coroutines.launch

class F442Fragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels {
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao()
        )
    }

    private var binding: FragmentF442Binding? = null
    lateinit var item: EntityX

    private val navigationArgs: F442FragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentF442Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            fragmentf442 = this@F442Fragment
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu2)

        binding?.lineupToolbar?.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.formation -> {
                    val dialog = ForDialog { selectedFormation(it) }
                    dialog.show(parentFragmentManager, "formation_dialog")
                    true
                }
                R.id.players -> {
                    findNavController().navigate(R.id.action_f442Fragment_to_playersFragment)
                    true
                }
                R.id.save -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Do you save?")
                        .setPositiveButton("OK") { _, _ ->

                        }
                        .setNegativeButton("CANCEL") { _, _ ->
                            Toast.makeText(requireContext(), "cancel", Toast.LENGTH_LONG).show()
                        }
                        .show()
                    true
                }
                else -> false
            }
        }
    }

    fun goToPlayerList(position: Int) {
        lineupViewModel.setPositionId(position)

        val action = F442FragmentDirections.actionF442FragmentToPlayersFragment(
            position, 442
        )
        this.findNavController().navigate(action)
    }

    private fun selectedFormation(item: String) {
        when (item) {
            "3-1-4-2" -> {
                findNavController().navigate(R.id.action_f442Fragment_to_f3142Fragment)
            }
            "4-4-2" -> {
                Toast.makeText(context, "here!", Toast.LENGTH_LONG).show()
            }
            "5-3-2" -> {
                findNavController().navigate(R.id.action_f442Fragment_to_f532Fragment)
            }
            "5-4-1" -> {
                findNavController().navigate(R.id.action_f442Fragment_to_f541Fragment)
            }
        }
    }

//    private fun checkMap() {
//        if (lineupViewModel.mapPositionPlayer.count() == 11) {
//
//        } else {
//            Toast.makeText(requireContext(), "Not enough players!", Toast.LENGTH_LONG).show()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }
}