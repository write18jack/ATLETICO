package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.atletico.R
import com.example.atletico.databinding.FragmentImageAdapterBinding

class ImageAdapterFragment : Fragment() {
    private inner class MyAdapter(
        fa: ImageAdapterFragment, private val listener: ImageAdapterFragment
    ): FragmentStateAdapter(fa) {
        private val resources = listOf(
            Pager("4-4-2", R.drawable.f442),
            Pager("3-1-4-2", R.drawable.f3142)
        )

        override fun getItemCount(): Int = resources.size

        override fun createFragment(position: Int): Fragment {
            ImageFragment.newInstance(resources[position])

        }

    }
    private lateinit var binding: FragmentImageAdapterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("Tag", "IAF onCreateView:"+ lifecycle.currentState)
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentImageAdapterBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.pager.adapter = MyAdapter(this, this@ImageAdapterFragment)
    }
}