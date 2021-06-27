package com.bradley.fragmentscrollretain.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bradley.fragmentscrollretain.R
import com.bradley.fragmentscrollretain.databinding.FragmentMainRecyclerViewBinding
import com.bradley.fragmentscrollretain.databinding.ItemDataBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainRecyclerViewFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainRecyclerViewBinding

    private lateinit var adapter: ItemDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ItemDataAdapter {
            findNavController().navigate(R.id.action_main_recycler_view_fragment_to_detail_fragment)
        }
        //adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainRecyclerViewBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerView.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1000)
            val data = withContext(Dispatchers.IO) {
                generateSequence(0) { it + 1 }.map { "position : $it" }.take(20).toList()
            }
            adapter.submitList(data)
        }

    }

}

class ItemDataAdapter(
    private val onClick: () -> Unit
) : ListAdapter<String, ItemDataViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataViewHolder =
        ItemDataViewHolder(
            ItemDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ItemDataViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
        oldItem == newItem
}

class ItemDataViewHolder(
    private val viewDataBinding: ItemDataBinding
) : RecyclerView.ViewHolder(viewDataBinding.root) {

    fun bind(data: String, onClick: () -> Unit) {
        viewDataBinding.dataView.text = data
        viewDataBinding.button.setOnClickListener {
            onClick()
        }
    }

}
