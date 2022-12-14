package com.example.atletico.adapter

import android.util.Log
import android.view.Display
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.atletico.R
import com.example.atletico.ui.schedule.DisplayTable
import com.example.atletico.ui.schedule.StandingsAdapter

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("android:iconId")
    fun setIconBy(view: com.google.android.material.imageview.ShapeableImageView, icon: Int) {
        Log.d("TAG", "BindingAdapter iconId: $icon")

        val iconId = when (icon) {
            1->R.drawable.felix
            2->R.drawable.correa
            3->R.drawable.griezmann
            4->R.drawable.cunha
            5->R.drawable.morata
            6->R.drawable.camello
            7->R.drawable.paulo
            8->R.drawable.carrasco
            9->R.drawable.lemar2
            10->R.drawable.lino
            11->R.drawable.roro
            12->R.drawable.ricard
            13->R.drawable.koke
            14->R.drawable.saul
            15->R.drawable.depaul
            16->R.drawable.witsel
            17->R.drawable.lodi
            18->R.drawable.manu2
            19->R.drawable.reinildo
            20->R.drawable.molina
            21->R.drawable.gimenez
            22->R.drawable.savic
            23->R.drawable.felipe
            24->R.drawable.perez
            25->R.drawable.obrak
            else -> null
        }
        iconId?.let {
            view.setBackgroundResource(it)
        } ?: view.setImageDrawable(null)
    }

    @JvmStatic
    @BindingAdapter("android:players")
    fun setPlayerBy(view:com.google.android.material.imageview.ShapeableImageView, Image: Int) {
        Log.d("TAG", "BindingAdapter Image: $Image")

        Image.let {
            view.setBackgroundResource(it)
        }
    }

    @JvmStatic
    @BindingAdapter("listData")
    fun bindRecyclerView(recylerView: RecyclerView, data: List<DisplayTable>?){
        val adapter = recylerView.adapter as StandingsAdapter
        adapter.submitList(data)

    }
}