package com.example.atletico.ui.lineup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.atletico.databinding.FragmentImageBinding

private const val IMG_RES_NAME = "IMG_RES_NAME"
private const val IMG_RES_ID = "IMG_RES_ID"

class ImageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var imageResName: String? = null
    private var imageResId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            imageResName = it.getString(IMG_RES_NAME)
            imageResId = it.getInt(IMG_RES_ID)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageResName?.let{
            binding?.pagerText?.text = it
        }
        imageResId?.let {
            binding?.pagerBtn?.setBackgroundResource(it)
        }
    }

    private var binding: FragmentImageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentImageBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
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