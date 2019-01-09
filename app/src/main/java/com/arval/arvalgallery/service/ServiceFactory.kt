package com.arval.arvalgallery.service

import android.util.Log

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit

import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ServiceFactory {
    private val HTTP_READ_TIMEOUT = 10000
    private val HTTP_CONNECT_TIMEOUT = 6000
    private val ENDPOINT = "https://api"

    fun createService(): NetworkService {
        return initService(makeOkHttpClient())
    }


    private fun initService(okHttpClient: OkHttpClient): NetworkService {
        val gson = GsonBuilder()
                .create()

        val retrofit = Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
        return retrofit.create(NetworkService::class.java)
    }

    private fun makeOkHttpClient(): OkHttpClient {
        val okHttpClient: OkHttpClient
        try {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory)
            builder.connectTimeout(HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            builder.readTimeout(HTTP_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            okHttpClient = builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

        return okHttpClient
    }

}