package com.arval

import android.graphics.Bitmap
import android.util.LruCache
import java.io.File

/**
 * Created by Arvel on 09/01/2019.
 */

class MemoryCache : Cache {

    val cache: LruCache<String, File>
    var maxLimit: Long = 1000000 //1MB
    var expireTime: Long = 10000 //10s


    init {
        maxLimit = Runtime.getRuntime().maxMemory() / 4
        cache = object : LruCache<String, File>(maxLimit.toInt()) {
            override fun sizeOf(key: String?, value: File?): Int {
                return value?.length()?.toInt()!!
            }
        }
    }

    override fun put(key: String, value: Any) {
        cache.put(key, value as File?)
    }

    override fun get(key: String): Any? {
        return cache.get(key)
    }

    override fun remove(key: String) {
        cache.remove(key)
    }

    override fun clear() {
        cache.evictAll()
    }

}

