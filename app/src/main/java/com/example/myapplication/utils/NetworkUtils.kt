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

    /* get the beer data, accepts the app context to send the resulting intent to the correct place
    * ctx: the context from the MainActivity to return an Intent
    * */
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

    /* return the data back to the activities
    * data: the json from the API
    * ctx: the context from the MainActivity where the intent originates
    * */
    fun sendIntent(data: String, ctx: MainActivity){
        val intent = Intent(ctx, DiplayListActivity::class.java)
        intent.putExtra("data", data);
        ctx.startActivity(intent)
    }

    /* pass the imageurl to fetch, and the item to render once the data is returned
    * imgUrl: the url for the beer logo from the Beer class
    * item: an instance of the BeerViewHolder
    * */
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
                mainHandler.post {
                    var logo: ImageView = item.findViewById(R.id.logo)
                    var progress: ProgressBar = item.findViewById(R.id.progress_loader)
                    logo.setImageResource(R.drawable.icon)
                    logo.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                }
                e.printStackTrace()
            }
        }.start()
    }
}