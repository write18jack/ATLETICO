package com.example.atletico.ui.lineup

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.atletico.R
import com.example.atletico.databinding.FragmentPlayersBinding
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFragment : Fragment(), RecyclerViewClickListener {
    private val lineupViewModel: LineupViewModel by activityViewModels {
        LineupViewModelFactory(
            (activity?.application as SaveLineUpApplication).database
                .itemDao(),
            (activity?.application as SaveLineUpApplication).database
                .formationItemDao()
        )
    }

    private var binding: FragmentPlayersBinding? = null
    private val navigationArgs: PlayersFragmentArgs by navArgs()
    lateinit var item: EntityX

    private val playerList = listOf(
        Players(R.drawable.felix, 1),
        Players(R.drawable.correa, 2),
        Players(R.drawable.griezmann, 3),
        Players(R.drawable.cunha, 4),
        Players(R.drawable.morata, 5),
        Players(R.drawable.camello, 6),
        Players(R.drawable.paulo, 7),
        Players(R.drawable.carrasco, 8),
        Players(R.drawable.lemar2, 9),
        Players(R.drawable.lino, 10),
        Players(R.drawable.roro, 11),
        Players(R.drawable.ricard, 12),
        Players(R.drawable.koke, 13),
        Players(R.drawable.saul, 14),
        Players(R.drawable.rodrigo, 15),
        Players(R.drawable.witsel, 16),
        Players(R.drawable.lodi, 17),
        Players(R.drawable.manu, 18),
        Players(R.drawable.reinildo, 19),
        Players(R.drawable.molina, 20),
        Players(R.drawable.gimenez, 21),
        Players(R.drawable.savic, 22),
        Players(R.drawable.felipe, 23),
        Players(R.drawable.perez, 24),
        Players(R.drawable.obrak, 25)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentPlayersBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            playersFragment = this@PlayersFragment
            playersRecycler.adapter = PlayersAdapter(playerList, this@PlayersFragment)
            players_recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onRecyclerViewItemClick(view: View, Item: Players) {
        //positionId
        val positionIdInPF = navigationArgs.itemId
        // When Position in Map
        if (positionIdInPF in lineupViewModel.mapPositionPlayer) {
            // When Player in Map
            if (lineupViewModel.mapPositionPlayer.containsValue(Item.id)) {

                if (lineupViewModel.mapPositionPlayer[positionIdInPF] == Item.id) {
                    lineupViewModel.updateItemx(positionIdInPF, Item.id)
                } else {
                    //Player ID set to change source
                    val previousID: Int? = lineupViewModel.mapPositionPlayer[positionIdInPF]

                    //Position ID to be modified
                    val changePosition = lineupViewModel.mapPositionPlayer.filterValues {
                        it == Item.id
                    }.keys.elementAt(0)

                    lineupViewModel.updateItemx(positionIdInPF, Item.id)
                    if (previousID != null) {
                        lineupViewModel.updateItemx(changePosition, previousID)
                    }
                }
            } else {
                lineupViewModel.updateItemx(positionIdInPF, Item.id)
            }
            //not position in Map
        } else if (lineupViewModel.mapPositionPlayer.containsValue(Item.id)) {
            Toast.makeText(requireContext(), "Duplicate players!", Toast.LENGTH_LONG).show()
        } else {
            lineupViewModel.setPlayerId(Item.id)
            lineupViewModel.select()
            lineupViewModel.addNewItem()
        }

        when (navigationArgs.formationId) {
            3142 -> {
                findNavController().navigate(R.id.action_playersFragment_to_f3142Fragment)
            }
            442 -> {
                findNavController().navigate(R.id.action_playersFragment_to_f442Fragment)
            }
            532 -> {
                findNavController().navigate(R.id.action_playersFragment_to_f532Fragment)
            }
            541 -> {
                findNavController().navigate(R.id.action_playersFragment_to_f541Fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        val inputMethodManager = requireActivity().getSystemService(INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        binding = null
    }
}