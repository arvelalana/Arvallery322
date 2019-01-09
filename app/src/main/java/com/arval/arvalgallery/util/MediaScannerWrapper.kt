package com.arval.arvalgallery.util

import android.content.Context
import android.media.MediaScannerConnection.scanFile
import android.media.MediaScannerConnection
import android.net.Uri
import android.util.Log


class MediaScannerWrapper// filePath - where to scan;
// mime type of media to scan i.e. "image/jpeg".
// use "*/*" for any media
(ctx: Context, private val mPath: String, private val mMimeType: String) : MediaScannerConnection.MediaScannerConnectionClient {
    private val mConnection: MediaScannerConnection

    init {
        mConnection = MediaScannerConnection(ctx, this)
    }

    // do the scanning
    fun scan() {
        mConnection.connect()
    }

    // start the scan when scanner is ready
    override fun onMediaScannerConnected() {
        mConnection.scanFile(mPath, mMimeType)
        Log.w("MediaScannerWrapper", "media file scanned: $mPath")
    }

    override fun onScanCompleted(path: String, uri: Uri) {
        // when scan is completes, update media file tags
        Log.i("onScanCompleted", "path = " + path + " uri = " + uri)
        mConnection.disconnect()
    }
}