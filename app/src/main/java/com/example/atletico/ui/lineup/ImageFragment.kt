package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.atletico.R
import com.example.atletico.databinding.FragmentImageBinding
import com.example.atletico.databinding.FragmentLineUpBinding
import kotlinx.android.synthetic.main.fragment_image.*
import kotlinx.android.synthetic.main.fragment_image_adapter.*

private const val IMG_RES_NAME = "IMG_RES_NAME"
private const val IMG_RES_ID = "IMG_RES_ID"

class ImageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageResName: String? = null
    private var imageResId: Int? = null

    private var binding: FragmentImageBinding? = null
    private var bindingx: FragmentLineUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Tag", "IF onCreate:"+ lifecycle.currentState)
        arguments?.let {
            imageResName = it.getString(IMG_RES_NAME)
            imageResId = it.getInt(IMG_RES_ID)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Tag", "IF onActivityCreate:" + lifecycle.currentState)
        imageResName?.let {
            binding?.pagerTxt?.text = it
        }
        imageResId?.let {
            binding?.pagerImg?.setBackgroundResource(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentImageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        Log.d("Tag", "IF onCreateView:"+ lifecycle.currentState)

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.pagerBtn?.setOnClickListener {
            when(binding?.pagerTxt?.text){
                "4-4-2"->{
                findNavController().navigate(R.id.action_lineupFragment_to_f442Fragment)
                }
                "3-1-4-2"->{
                    findNavController().navigate(R.id.action_lineupFragment_to_f3142Fragment)
                }
            }
        }

        binding?.pagerBackBtn?.setOnClickListener {
            refreshCurrentFragment()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Tag", "IAF onDestroyView:"+ lifecycle.currentState)
        binding = null
    }

    private fun refreshCurrentFragment(){
        val id = findNavController().currentDestination?.id
        findNavController().popBackStack(id!!, true)
        findNavController().navigate(id)
    }

    companion object {

        @JvmStatic
        fun newInstance(pager: Pager) =
            ImageFragment().apply {
                arguments = Bundle().apply {
                    putString(IMG_RES_NAME, pager.formation_name)
                    putInt(IMG_RES_ID, pager.formation_image)
                }
            }
    }
}