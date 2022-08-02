package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atletico.R
import com.example.atletico.databinding.FragmentPlayersBinding
import kotlinx.android.synthetic.main.fragment_players.*

class PlayersFragment : Fragment(), RecyclerViewClickListener{

    private var binding: FragmentPlayersBinding? = null
    private val lineupViewModel: LineupViewModel by activityViewModels()

    val playerList = listOf(
        Players(R.drawable.felix,1),
        Players(R.drawable.correa,2),
        Players(R.drawable.griezmann,3),
        Players(R.drawable.cunha,4),
        Players(R.drawable.morata,5),
        Players(R.drawable.camello,6),
        Players(R.drawable.paulo,7),
        Players(R.drawable.carrasco,8),
        Players(R.drawable.lemar2,9),
        Players(R.drawable.lino,10),
        Players(R.drawable.roro,11),
        Players(R.drawable.ricard,12),
        Players(R.drawable.koke,13),
        Players(R.drawable.saul,14),
        Players(R.drawable.rodrigo,15),
        Players(R.drawable.witsel,16),
        Players(R.drawable.lodi,17),
        Players(R.drawable.manu,18),
        Players(R.drawable.reinildo,19),
        Players(R.drawable.wass,20),
        Players(R.drawable.gimenez,21),
        Players(R.drawable.savic,22),
        Players(R.drawable.felipe,23),
        Players(R.drawable.perez,24),
        Players(R.drawable.obrak,25)
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

        setFragmentResultListener(
            "REQUEST_KEY"
        ){_, bundle ->
            val id = bundle.getInt("KEY")
            lineupViewModel.setPositionId(id)
        }

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = lineupViewModel
            playersFragment = this@PlayersFragment
            playersRecycler.adapter = PlayersAdapter(playerList, this@PlayersFragment)
            players_recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onRecyclerViewItemClick(view: View, Item: Players) {
        lineupViewModel.setPlayerId(Item.id)
        lineupViewModel.select()
        findNavController().navigate(R.id.action_playersFragment_to_f442Fragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}