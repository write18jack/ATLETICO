package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.atletico.R

class ForDialog: DialogFragment(){

    private val items = arrayOf("3-1-4-2", "4-4-2", "5-3-2", "5-4-1")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        return parentFragment?.let {

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle(R.string.formation)
            builder.setItems(items){ _, which ->
                when(which){
                    0 -> {
                        findNavController().navigate(R.id.action_forDialog_to_f3142Fragment)
                    }
                    1 -> {
                        findNavController().navigate(R.id.action_forDialog_to_f442Fragment)
                    }
                    2 -> {
                        findNavController().navigate(R.id.action_forDialog_to_f532Fragment)
                    }
                    3 -> {
                        findNavController().navigate(R.id.action_forDialog_to_f541Fragment)
                    }
                }
            }
            return builder.create()
        }?: throw IllegalStateException("Exception!! Activity is null")
    }
}