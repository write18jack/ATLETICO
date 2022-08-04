package com.example.atletico.ui.lineup

import android.view.View

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, Item: Players)
}
interface ViewPagerClickListener {
    fun onViewPagerClickListener(view:View, Item: Pager)
}