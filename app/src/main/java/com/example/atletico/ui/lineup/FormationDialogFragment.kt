package com.example.atletico.ui.lineup

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.atletico.R

class FormationDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Formation")
            .setItems(R.array.spinner_items){ dialog, which ->
                val langs = resources.getStringArray(R.array.spinner_items)
                println(langs[which])
            }

        return builder.create()
    }

}