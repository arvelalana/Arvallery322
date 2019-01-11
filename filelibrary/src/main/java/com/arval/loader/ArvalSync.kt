package com.arval.loader

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.io.*
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Created by Arvel on 11/01/2019.
 */

class ArvalSync(fileType: Type, delegate: ArvalCallback) : AsyncTask<String, Any, Any>() {

    var delegate: ArvalCallback? = delegate
    var type: Type = fileType
    var cache: ArvalCache = ArvalCache()
    var executorService: ExecutorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    val uiHandler: Handler = Handler(Looper.getMainLooper())


    override fun doInBackground(vararg parts: String?): Any? {

        Log.i("doInBackground :", parts.first().toString())
        Log.i("doInBackground :", parts.toString())

        val requestURL = parts.first()
        val mURL = URL(requestURL)

        if(cache.get(requestURL.toString())!=null){
            return cache.get(requestURL.toString())
        }

        with(mURL.openConnection() as HttpURLConnection) {
            // optional default is GET
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream)).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                println(response.toString())
                val jsonResponse = loadJSON(response.toString())
                cache.put(requestURL.toString(),jsonResponse.toString())
                return jsonResponse
            }
        }
    }

    override fun onPostExecute(result: Any?) {

        println("WAKANDA")
        delegate?.onFinish(result)
    }

    fun loadJSON(jsonString:String): Any? {
        try {
            val gson = Gson()
            return gson.fromJson<Any>(jsonString, type)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

    }
}