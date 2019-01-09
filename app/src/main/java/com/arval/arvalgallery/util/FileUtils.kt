package com.arval.arvalgallery.util

import android.content.Context
import android.provider.MediaStore


object FileUtils {

    fun getImagePaths(context: Context): List<String> {
        // The list of columns we're interested in:
        val columns = arrayOf(MediaStore.Images.Media.DATA, MediaStore.Images.Media.DATE_ADDED)

        val cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // Specify the provider
                columns, // A WHERE-filter query
                null, null, // The arguments for the filter-query
                MediaStore.Images.Media.DATE_ADDED + " DESC" // Order the results, newest first
        )// The columns we're interested in

        val result = ArrayList<String>(cursor.getCount())

        if (cursor.moveToFirst()) {
            val image_path_col = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            do {
                result.add(cursor.getString(image_path_col))
            } while (cursor.moveToNext())
        }
        cursor.close()

        return result
    }
}