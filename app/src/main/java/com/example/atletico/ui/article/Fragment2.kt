package com.example.atletico.ui.article

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.atletico.R
import com.example.atletico.databinding.Fragment2Binding


class Fragment2 : Fragment() {

    private var binding: Fragment2Binding? = null
    private val articleViewModel: ArticleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TT", "fragment2")
        // Inflate the layout for this fragment
        val fragmentBinding = Fragment2Binding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = articleViewModel
            fragment2 = this@Fragment2
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}