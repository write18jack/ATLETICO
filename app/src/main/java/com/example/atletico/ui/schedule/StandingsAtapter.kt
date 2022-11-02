package com.example.atletico.ui.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.atletico.databinding.StandingsItemBinding

class StandingsAdapter : ListAdapter<Display, StandingsAdapter.StandingsViewHolder>(DiffCallback) {

        class StandingsViewHolder(
                private var binding: StandingsItemBinding
                ) : RecyclerView.ViewHolder(binding.root) {
                fun bind(display: Display) {
                        Log.d("StandingsAdapter", "display.rank: ${display.rank}")
                        binding.team = display
                        binding.executePendingBindings()
                }
        }

        companion object DiffCallback: DiffUtil.ItemCallback<Display>(){
                override fun areItemsTheSame(oldItem: Display, newItem: Display): Boolean {
                        return oldItem.rank == newItem.rank
                }

                override fun areContentsTheSame(oldItem: Display, newItem: Display): Boolean {
                        return oldItem.name == newItem.name
                }
        }

        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): StandingsViewHolder {
                return StandingsViewHolder(
                        StandingsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                )
        }

        override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
                holder.bind(getItem(position))
        }
}

