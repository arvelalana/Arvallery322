package com.arval

import android.os.Handler
import android.telecom.Call
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
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * Created by Arvel on 10/01/2019.
 */

object DownloadTasker{

    fun downloadFile(url: String, dir: File): File? {
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        val name:String = ""
        val fileExt:String = ""


        val response = client.newCall(request).execute()
        val contentType = response.header("content-type", null)
        var ext = MimeTypeMap.getSingleton().getExtensionFromMimeType(contentType)
        ext = if (ext == null) {
            fileExt
        } else {
            ".$ext"
        }

        // use provided name or generate a temp file
        var file: File? = null
        file = if (name != null) {
            val filename = String.format("%s%s", name, ext)
            File(dir.absolutePath, filename)
        } else {
//            val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-kkmmss"))
            File.createTempFile("temp", ext, dir)
        }

        val body = response.body()
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