package com.arval.loader

/**
 * Created by Arvel on 08/01/2019.
 */
interface Cache {
    fun put(key: String, value: Any)
    fun get(key: String): Any?
    fun remove(key: String)
    fun clear()

}