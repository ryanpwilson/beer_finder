package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.myapplication.utils.NetworkUtils


class MainActivity : AppCompatActivity() {
    private lateinit var but: Button
    private lateinit var load: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        but = findViewById(R.id.button)
        load = findViewById(R.id.progress_loader)
        var NetUtil = NetworkUtils()
        but.setOnClickListener {
            load.visibility = View.VISIBLE
            but.visibility = View.GONE
            NetUtil.sendGet(this)
        }
    }

    override fun onResume() {
        super.onResume()
        load.visibility = View.GONE
        but.visibility = View.VISIBLE
    }
}