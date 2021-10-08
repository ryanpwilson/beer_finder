package com.example.myapplication.utils

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.example.myapplication.DiplayListActivity
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import okhttp3.*
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class NetworkUtils {
    var mainHandler = Handler(Looper.getMainLooper())

     fun sendGet(ctx: MainActivity) {
        var client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.punkapi.com/v2/beers")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                mainHandler.post {
                    val body = response.body?.string()
                    if (body == null) return@post
                    sendIntent(body, ctx)
                }
            }
        })
    }

    fun sendIntent(data: String, ctx: MainActivity){
        val intent = Intent(ctx, DiplayListActivity::class.java)
        intent.putExtra("data", data);
        ctx.startActivity(intent)
    }

    fun getBitmapFromURL(imgUrl: String?, item: View){

        Thread {
            try {
                val url = URL(imgUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.setDoInput(true)
                connection.connect()
                val input: InputStream = connection.getInputStream()
                val bitmap = BitmapFactory.decodeStream(input)

                mainHandler.post {
                    var logo: ImageView = item.findViewById(R.id.logo)
                    var progress: ProgressBar = item.findViewById(R.id.progress_loader)
                    logo.setImageBitmap(bitmap)
                    logo.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()
    }
}