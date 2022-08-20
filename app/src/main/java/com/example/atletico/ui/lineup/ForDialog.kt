package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FormationDialogBinding

class ForDialog(
    private val formation_Selected: (x:String)->Unit

) : DialogFragment(){
    val items = arrayOf("3-1-4-2", "4-4-2")

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