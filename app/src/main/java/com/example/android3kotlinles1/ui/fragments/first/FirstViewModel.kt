package com.example.android3kotlinles1.ui.fragments.first

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android3kotlinles1.models.Model

class FirstViewModel : ViewModel() {
    val mutableLiveData = MutableLiveData<ArrayList<Model>>()
    var list = ArrayList<Model>()

    init {
        fillList()
    }

    private fun fillList() {
        list.add(Model("qwerty"))
        mutableLiveData.value = list
    }
}