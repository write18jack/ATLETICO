package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.atletico.R

class ForDialog(
    private val formation_Selected: (x:String)->Unit
) : DialogFragment(){

    val items = arrayOf("3-1-4-2", "4-4-2", "5-3-2", "5-4-1")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return parentFragment?.let {

            val builder = AlertDialog.Builder(requireActivity())
            builder.setTitle(R.string.formation)
            builder.setItems(items){ dialog, which ->
                formation_Selected(items[which])
            }
            return builder.create()
        }?: throw IllegalStateException("Exception!! Activity is null")
    }
}