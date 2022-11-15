package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF442Binding

class F442Fragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels {
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database.itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }
    private var binding: FragmentF442Binding? = null
    lateinit var item: EntityX

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

        lineupViewModel.renewalFormation(1,"4-4-2")

        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu2)
        binding?.lineupToolbar?.setOnMenuItemClickListener { it ->
            when (it.itemId) {
                R.id.formation -> {
                    findNavController().navigate(R.id.action_f442Fragment_to_forDialog)
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
//        requireActivity().onBackPressedDispatcher.addCallback(requireActivity()){
//            findNavController().navigate(R.id.action_f442Fragment_to_lineupFragment)
//        }
    }

    fun goToPlayerList(position: Int) {
        lineupViewModel.setPositionId(position)

        val action = F442FragmentDirections.actionF442FragmentToPlayersFragment(
            position, 442
        )
        this.findNavController().navigate(action)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }
}