package com.example.android3kotlinles1.ui.fragments.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android3kotlinles1.R
import com.example.android3kotlinles1.adapters.Adapter
import com.example.android3kotlinles1.adapters.clickers.OnItemClick
import com.example.android3kotlinles1.base.BaseFragment
import com.example.android3kotlinles1.databinding.FragmentFirstBinding
import com.example.android3kotlinles1.models.Model


class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override lateinit var binding: FragmentFirstBinding
    private lateinit var firstViewModel: FirstViewModel
    private val adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        firstViewModel = ViewModelProvider(this).get(FirstViewModel::class.java)
        return binding.root
    }

    override fun setupViews() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setOnClickListener(View.OnClickListener {
            getData()
        })
    }

    override fun setupRequest() {
        getData()
        firstViewModel.mutableLiveData.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    override fun setupListeners() {
        adapter.onItemClickListener(object : OnItemClick {
            override fun onItemClickListener(model: Model) {
                findNavController().navigate(R.id.secondFragment)
            }
        })
    }

    private fun getData() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Bundle>("any")
            ?.observe(viewLifecycleOwner) { result ->
                firstViewModel.list.add(Model(result.getString("anyText")!!))
            }
    }
}