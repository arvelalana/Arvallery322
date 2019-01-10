package com.arval

import android.app.Instrumentation
import android.graphics.Bitmap
import android.util.LruCache
import java.io.File
import java.util.*
import java.util.Collections.synchronizedMap
import android.icu.lang.UCharacter.GraphemeClusterBreak.V


/**
 * Created by Arvel on 08/01/2019.
 */

//class MemoryCache(maxSize: Int) : LruCache<String, Objects>(maxSize) {
//
//
//    override fun resize(maxSize: Int) {
//        super.resize(maxSize)
//    }
//
//    override fun create(key: String?): Objects {
//        return super.create(key)
//    }
//
//    override fun entryRemoved(evicted: Boolean, key: String?, oldValue: Objects?, newValue: Objects?) {
//        super.entryRemoved(evicted, key, oldValue, newValue)
//    }
//
//    override fun sizeOf(key: String?, value: Objects?): Int {
//        return super.sizeOf(key, value)
//    }
//
//    override fun trimToSize(maxSize: Int) {
//        super.trimToSize(maxSize)
//    }
//
//}
class ImageCache : Cache {

    val cache: LruCache<String, Bitmap>
    var maxLimit: Long = 1000000 //1MB
    var expireTime: Long = 10000 //10s


    init {
        maxLimit = Runtime.getRuntime().maxMemory() / 4
        cache = object : LruCache<String, Bitmap>(maxLimit.toInt()) {
            override fun sizeOf(key: String?, bitmap: Bitmap?): Int {
                return (bitmap?.rowBytes ?: 0) * (bitmap?.height ?: 0)
            }
        }
    }

    override fun put(key: String, value: Any) {
        cache.put(key, value as Bitmap?)
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

