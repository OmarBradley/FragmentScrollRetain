package com.bradley.fragmentscrollretain.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bradley.fragmentscrollretain.R
import com.bradley.fragmentscrollretain.databinding.FragmentMainScrollBinding

class MainScrollFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainScrollBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainScrollBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_main_scroll_fragment_to_detail_fragment)
        }
    }

}