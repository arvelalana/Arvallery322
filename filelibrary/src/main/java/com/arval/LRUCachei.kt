package com.arval

/**
 * Created by Arvel on 1/10/2019.
 */
interface LRUCachei {
    val size: Int

    operator fun set(key: Any, value: Any)

    operator fun get(key: Any): Any?

    fun remove(key: Any): Any?

    fun clear()
}