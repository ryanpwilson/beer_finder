package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var but: Button
    private lateinit var load: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        but = findViewById(R.id.button)
        load = findViewById(R.id.progress_loader)
        but.setOnClickListener {
            load.visibility = View.VISIBLE
            but.visibility = View.GONE
            sendGet()
        }
    }

    override fun onResume() {
        super.onResume()
        load.visibility = View.GONE
        but.visibility = View.VISIBLE
    }

    fun sendGet() {
        var client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.punkapi.com/v2/beers")
            .build()
        var mainHandler = Handler(this@MainActivity.getMainLooper())
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                mainHandler.post {
                    val body = response.body?.string()
                    if (body == null) return@post


                    val intent = Intent(this@MainActivity, DiplayListActivity::class.java)
                    intent.putExtra("data", body);
                    startActivity(intent)
              }
            }
        })
    }
}