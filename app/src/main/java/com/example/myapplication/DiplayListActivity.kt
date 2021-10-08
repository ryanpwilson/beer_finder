package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Beer
import com.example.myapplication.ui.main.BeerListAdapter
import com.example.myapplication.ui.main.MainViewModel
import com.google.gson.GsonBuilder

class DiplayListActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diplay_list)
        mainrecycler = findViewById(R.id.recycler)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initialiseAdapter()

        val data = intent.getStringExtra("data")
        parseData(data)
    }

    private fun initialiseAdapter(){
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    fun parseData(data: String?){
        val gson = GsonBuilder().serializeNulls().create()
        val info: List<Beer> = gson.fromJson(data, Array<Beer>::class.java).toList()

        for (item in info){
            addData(item)
        }
    }

    fun observeData(){
        viewModel.lst.observe(this, Observer{
            mainrecycler.adapter= BeerListAdapter(viewModel, it, this)
        })
    }

    fun addData(beer: Beer){
        viewModel.add(beer)
        mainrecycler.adapter?.notifyDataSetChanged()
    }
}