package com.example.android3kotlinles1.ui.fragments.second

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android3kotlinles1.base.BaseFragment
import com.example.android3kotlinles1.databinding.FragmentSecondBinding

class SecondFragment : BaseFragment<FragmentSecondBinding>() {
    override lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupListeners() {
        binding.btnEdit.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("anyText", binding.etForSend.text.toString())
            findNavController().previousBackStackEntry?.savedStateHandle?.set("any", bundle)
            findNavController().navigateUp()
        }
    }
}