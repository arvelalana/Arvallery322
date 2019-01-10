package com.arval

import android.net.NetworkRequest
import android.util.Log
import okhttp3.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Arvel on 1/10/2019.
 */
object ArvalRequest {

    fun create(url: String, method: String) :String {
        val mURL = URL(url)
        with(mURL.openConnection() as HttpURLConnection) {
            requestMethod = "GET"

            BufferedReader(InputStreamReader(inputStream) as Reader?).use {
                val response = StringBuffer()

                var inputLine = it.readLine()
                while (inputLine != null) {
                    response.append(inputLine)
                    inputLine = it.readLine()
                }
                
//                println(response.toString())
                Log.i("response.toString() :", response.toString())
                return response.toString()
            }
        }
    }
}