package com.example.atletico.ui.lineup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentLineUpBinding

class LineupFragment : Fragment() {

    private var binding: FragmentLineUpBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = FragmentLineUpBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.lineupToolbar?.inflateMenu(R.menu.line_up_top_bar)

        binding?.lineupToolbar?.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.formation -> {
                    Log.d("Tag", "LineupF: ${it.itemId}")
                    val dialog = ForDialog(
                        {selectedFormation(it)}
//                        "Cancel",
//                        Toast.makeText(context, "Canceled", Toast.LENGTH_LONG).show()
                    )
                    dialog.show(parentFragmentManager, "formation_dialog")
                    true
                }
                R.id.players -> {
                    findNavController().navigate(R.id.action_lineupFragment_to_playersFragment)
                    true
                }
                else -> false
            }
        }
    }
//    private inner class MyAdapter(
//        fa: LineupFragment
//    ): FragmentStateAdapter(fa) {
//
//        val formationList = listOf(
//            Pager("4-4-2", R.drawable.f442),
//            Pager("3-1-4-2", R.drawable.f3142)
//        )
//
//        override fun getItemCount(): Int = formationList.size
//
//        //スライドでimageを表示する
//        override fun createFragment(position: Int): Fragment {
//            return ImageFragment.newInstance(formationList[position])
//        }
//    }
//    fun showAlert(){
//        val newFragment = ForDialog()
//        newFragment.show(parentFragmentManager, "formation")
//    }

    private fun selectedFormation(item: String){
        when(item){
            "3-1-4-2"->{findNavController().navigate(R.id.action_lineupFragment_to_f3142Fragment)}
            "4-4-2"->{findNavController().navigate(R.id.action_lineupFragment_to_f442Fragment)}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}