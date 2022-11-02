package com.example.atletico.ui.schedule

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atletico.databinding.FragmentScheduleBinding
import com.example.atletico.ui.lineup.LineupViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ScheduleFragment : Fragment() {

    private val viewModel: ScheduleViewModel by lazy {
        ViewModelProvider(this)[ScheduleViewModel::class.java]
    }
    private val standingsAdapter = StandingsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("ScheduleFragment", "onCreateView")
        val binding = FragmentScheduleBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this
        // Giving the binding access to the ScheduleViewModel
        binding.viewModel = viewModel

        binding.standingsRV.adapter = StandingsAdapter()

        //observerStandings()
        return binding.root
    }

    private fun observerStandings(){
        Log.d("ScheduleFragment", "observerStandings")
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.standings.collect{displays ->
                    updateDisplayList(displays)
                }
            }
        }
    }

    private fun updateDisplayList(displays: List<Display>){
        Log.d("ScheduleFragment", "updateDisplayList")
        standingsAdapter.submitList(displays)
    }
}