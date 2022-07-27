package com.example.atletico.ui.article

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentArticleBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log

class ArticleFragment : Fragment() {

    private val articleViewModel : ArticleViewModel by viewModels()
    private var binding: FragmentArticleBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentArticleBinding.inflate(inflater, container, false)
        Log.d("TT", "onCreateView ")
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TT", "onViewCreated")

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = articleViewModel
            fragment = this@ArticleFragment

        }

//        val button : Button = view.findViewById(R.id.btn)
//
//        button.setOnClickListener {
//            findNavController().navigate(R.id.action_articleFragment_to_fragment2)
//        }
    }


    fun gotonext(){
        Log.d("TT", "gotonext ")
        findNavController().navigate(R.id.action_articleFragment_to_fragment2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}