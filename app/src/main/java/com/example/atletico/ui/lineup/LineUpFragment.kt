package com.example.atletico.ui.lineup

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.example.atletico.R
import com.example.atletico.databinding.FragmentF442Binding
import com.example.atletico.databinding.FragmentLineUpBinding
import kotlinx.android.synthetic.main.fragment_line_up.view.*

class LineUpFragment : Fragment() {

    private val lineupviewModel: LineupViewModel by activityViewModels()
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

        binding?.lineupToolbar?.inflateMenu(R.menu.top_app_bar)
        //binding?.lineupToolbar?.setLogo(R.mipmap.ic_launcher)

        //(activity as AppCompatActivity).supportActionBar?.setLogo(R.mipmap.ic_launcher_foreground)
        binding?.lineupToolbar?.setOnMenuItemClickListener{
            when(it.itemId){
                R.id.formation -> {
                    true
                }
                R.id.players -> {
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}