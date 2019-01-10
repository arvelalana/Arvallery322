package com.arval

import android.os.AsyncTask
import android.os.Handler
import android.telecom.Call
import android.util.Log
import android.webkit.MimeTypeMap
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okio.Okio
import okio.buffer
import okio.sink
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.security.auth.callback.CallbackHandler
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by Arvel on 10/01/2019.
 */
class DownloadTasker(url:String,dir: File) : AsyncTask<String, Void, File>() {
    val url = url
    val dir =dir
    override fun doInBackground(vararg params: String?): File? {
        // ...
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val response = client.newCall(request).execute()
        return downloadFile(url,dir);
    }

    override fun onPreExecute() {
        super.onPreExecute()
        // ...
    }

    override fun onPostExecute(result: File?) {
        super.onPostExecute(result)

        Log.i("download :", result.toString())
        // ...
    }


    fun downloadFile(url: String, dir: File): File? {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
//        val name:String = null
        val fileExt:String = ""

        val response = client.newCall(request).execute()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Log.i("download body 2:", "fawe")
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

                Log.i("download body 2:", response.toString())
            }

        })
        val contentType = response.header("content-type", null)
        var ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentType)
        ext = if (ext == null) {
            fileExt
        } else {
            ".$ext"
        }

        // use provided name or generate a temp file
        var file: File? = null
//        file = if (name != null) {
//            val filename = String.format("%s%s", name, ext)
//            File(dir.absolutePath, filename)
//        } else {
//            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-kkmmss"))
            File.createTempFile("temp", ext, dir)
//        }

        val body = response.body()

        Log.i("download body :", response.toString())
//        Log.i("download File file:", file.toString())
//        Log.i("download File dir:", dir.toString())
        val sink = file!!.sink().buffer()
        /*
        sink.writeAll(body!!.source())
        sink.close()
        body.close()
         */

        body?.source().use { input ->
            sink.use { output ->
                output.writeAll(input!!)
            }
        }

        return file
    }

}

//object DownloadTasker{



//}