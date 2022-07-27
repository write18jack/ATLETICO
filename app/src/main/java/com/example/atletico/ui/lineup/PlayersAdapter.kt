package com.example.atletico.ui.lineup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.atletico.R
import com.example.atletico.databinding.PlayerCellBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class PlayersAdapter(
    private val playerList: List<Players>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    //viewの初期化
    inner class ViewHolder(val binding: PlayerCellBinding) : RecyclerView.ViewHolder(binding.root){
        //sub_layout.xml 1行だけのレイアウト
        fun bind(item: Players){
            binding.listItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.player_cell, parent, false))

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(playerList[position])
        viewHolder.binding.cardPalayer.setOnClickListener {
            listener.onRecyclerViewItemClick(viewHolder.binding.cardPalayer, playerList[position])
        }
    }
    override fun getItemCount() = playerList.size
}