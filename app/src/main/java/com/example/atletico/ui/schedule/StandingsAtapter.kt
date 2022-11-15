package com.example.atletico.ui.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.atletico.databinding.StandingsItemBinding

class StandingsAdapter : ListAdapter<DisplayTable, StandingsAdapter.StandingsViewHolder>(DiffCallback) {

        class StandingsViewHolder(
                private var binding: StandingsItemBinding
                ) : RecyclerView.ViewHolder(binding.root) {
                fun bind(displayTable: DisplayTable) {
                        binding.displayTable = displayTable
//                        binding.tableTeam.text = displayTable.team
//                        binding.tablePlayed.text = displayTable.played.toString()
//                        binding.tableWin.text = displayTable.win.toString()
//                        binding.tableDraw.text = displayTable.draw.toString()
//                        binding.tableLoss.text = displayTable.loss.toString()
//                        binding.tablePoints.text = displayTable.points.toString()
                        binding.executePendingBindings()
                }
        }

        companion object DiffCallback: DiffUtil.ItemCallback<DisplayTable>(){
                override fun areItemsTheSame(oldItem: DisplayTable, newItem: DisplayTable): Boolean {
                        return oldItem.team == newItem.team
                }

                override fun areContentsTheSame(oldItem: DisplayTable, newItem: DisplayTable): Boolean {
                        return oldItem.points == newItem.points
                }
        }

        override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
        ): StandingsViewHolder {
                return StandingsViewHolder(
                        StandingsItemBinding.inflate(
                                LayoutInflater.from(parent.context),
                                parent,
                                false
                        )
                )
        }

        override fun onBindViewHolder(holder: StandingsViewHolder, position: Int) {
                holder.bind(getItem(position))
        }
}

