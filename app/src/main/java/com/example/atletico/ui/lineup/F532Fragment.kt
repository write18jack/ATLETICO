package com.example.atletico.ui.lineup

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF442Binding
import com.example.atletico.databinding.FragmentF532Binding

class F532Fragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels {
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }

    private var binding: FragmentF532Binding? = null
    lateinit var item: EntityX

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentF532Binding.inflate(inflater, container, false)
        binding = fragmentBinding

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            fragment532 = this@F532Fragment
        }
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lineupViewModel.renewalFormation(1,"5-3-2")
        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu)

        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.formation -> {
                    Log.d("Tag", "LineupF: ${it.itemId}")
                    val dialog = ForDialog { selectedFormation(it) }
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

    fun goToPlayerList(position: Int) {
        lineupViewModel.setPositionId(position)

        val action = F532FragmentDirections.actionF532FragmentToPlayersFragment(
            position, 532
        )
        this.findNavController().navigate(action)
    }

    private fun selectedFormation(item: String) {
        when (item) {
            "3-1-4-2" -> {
                findNavController().navigate(R.id.action_f532Fragment_to_f3142Fragment)
            }
            "4-4-2" -> {
                findNavController().navigate(R.id.action_f532Fragment_to_f442Fragment)
            }
            "5-3-2" -> {
                Toast.makeText(context, "here!", Toast.LENGTH_LONG).show()
            }
            "5-4-1" -> {
                findNavController().navigate(R.id.action_f532Fragment_to_f541Fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }
}