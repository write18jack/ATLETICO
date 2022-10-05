package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentLineUpBinding
import kotlinx.coroutines.launch

class LineupFragment : Fragment() {
    private val lineupViewModel: LineupViewModel by activityViewModels{
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao()
        )
    }

    private var binding: FragmentLineUpBinding? = null
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

        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_menu)

        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.formation -> {
                    Log.d("Tag", "LineupF: ${it.itemId}")
                    val dialog = ForDialog { it1 -> selectedFormation(it1) }
                    dialog.show(parentFragmentManager, "formation_dialog")
                    true
                }
                R.id.players -> {
                    findNavController().navigate(R.id.action_lineupFragment_to_playersFragment)
                    true
                }
                else -> false
            }
        }
        /*
        * flow migrate to F442 */
        lifecycle.coroutineScope.launch{
            lineupViewModel.allItems().collect(){
                Log.d("XXX", "F442 List: $it")
                for (i in it){
                    lineupViewModel.setPositionId(i.itemPosition)
                    lineupViewModel.setPlayerId(i.itemPlayer)
                    lineupViewModel.select()
                }
            }
        }
    }

    private fun selectedFormation(item: String){
        when(item){
            "3-1-4-2"->{findNavController().navigate(R.id.action_lineupFragment_to_f3142Fragment)}
            "4-4-2"->{findNavController().navigate(R.id.action_lineupFragment_to_f442Fragment)}
            "5-3-2"->{findNavController().navigate(R.id.action_lineupFragment_to_f532Fragment)}
            "5-4-1"->{findNavController().navigate(R.id.action_lineupFragment_to_f541Fragment)}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}