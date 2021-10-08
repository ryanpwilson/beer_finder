package com.example.myapplication.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.models.Beer

class MainViewModel : ViewModel() {
    var lst = MutableLiveData<ArrayList<Beer>>()
    var newlist = arrayListOf<Beer>()

    fun add(beer: Beer){
        newlist.add(beer)
        lst.value=newlist
    }

    fun remove(beer: Beer){
        newlist.remove(beer)
        lst.value=newlist
    }
}