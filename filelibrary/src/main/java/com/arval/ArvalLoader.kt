package com.arval

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import com.google.gson.Gson
import okio.buffer
import okio.sink
import okio.source
import java.io.*
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Arvel on 08/01/2019.
 */

object ArvalLoader {
    private lateinit var cache: ArvalCache
    private var executorService: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val uiHandler: Handler = Handler(Looper.getMainLooper())

    fun setCache(cache: ArvalCache) {
        this.cache = cache
    }

    fun createRequest(url: String) {
        val cached = cache.get(url)
        if (cached != null) {
            return
        }

        executorService.submit {
            val inputStream: InputStream? = downloadFile(url)
            BufferedReader(InputStreamReader(inputStream) as Reader?).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
//                println(response.toString())
                if (response.toString() != null) {
                    cache.put(url, response.toString())
                }
                Log.i("response.toString() :", response.toString())
            }

        }
    }


    fun loadJSONFromAsset(type: Type): Any? {
        var json: String? = null
        try {
            val gson = Gson()
            return gson.fromJson<Any>(json, type)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
    }

    fun loadImage(url: String, imageView: ImageView) {
        val cached = cache.get(url)
        if (cached != null) {
            updateImageView(imageView, cached as Bitmap)
            return
        }
        imageView.tag = url
        executorService.submit {
            val bitmap: Bitmap? = downloadImage(url)
            if (bitmap != null) {
                if (imageView.tag == url) {
                    updateImageView(imageView, bitmap)
                }
                cache.put(url, bitmap)
            }
        }
    }

    fun clearCache() {
        this.cache.clear()
    }

    private fun updateImageView(imageView: ImageView, bitmap: Bitmap) {
        uiHandler.post {
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun downloadFile(url: String): InputStream? {
        var inputStream: InputStream? = null
        try {
            val url = URL(url)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            inputStream = conn.inputStream
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return inputStream
    }

    private fun downloadImage(url: String): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(url)
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection
            bitmap = BitmapFactory.decodeStream(conn.inputStream)
            conn.disconnect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    fun copyToFile(inputStream: InputStream, outputFile: File) {
        val source = inputStream.source().buffer()
        val sink = outputFile.sink().buffer()

        source.use { input ->
            sink.use { output ->
                output.writeAll(input)
            }
        }
    }

}