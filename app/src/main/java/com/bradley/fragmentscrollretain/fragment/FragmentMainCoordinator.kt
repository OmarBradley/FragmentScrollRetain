package com.bradley.fragmentscrollretain.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bradley.fragmentscrollretain.R
import com.bradley.fragmentscrollretain.databinding.FragmentMainCoordinatorBinding

class FragmentMainCoordinator : Fragment() {

    private lateinit var viewDataBinding: FragmentMainCoordinatorBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentMainCoordinatorBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.button.setOnClickListener {
            findNavController().navigate(R.id.action_main_coordinator_fragment_to_detail_fragment)
        }
    }


}