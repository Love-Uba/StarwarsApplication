package com.loveuba.starwarsapplication.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.*
import javax.security.cert.CertificateException
//import javax.net.ssl.TrustManager
//import javax.net.ssl.X509TrustManager
//
//
//val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
//    object : X509TrustManager {
//        override fun checkClientTrusted(
//            p0: Array<out java.security.cert.X509Certificate>?,
//            p1: String?
//        ) {
//        }
//
//        override fun checkServerTrusted(
//            p0: Array<out java.security.cert.X509Certificate>?,
//            p1: String?
//        ) {
//        }
//
//        override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
//        }
//    }
//)


class UnsafeOkHttpClient {

    fun getUnsafeOkHttpClient(): OkHttpClient? {

        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts: Array<TrustManager> = arrayOf<TrustManager>(
                object : X509TrustManager {
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(
                        chain: Array<X509Certificate?>?,
                        authType: String?
                    ) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate> {
                        return emptyArray<X509Certificate>()
                    }

                }
            )

            // Install the all-trusting trust manager
            val sslContext: SSLContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory: SSLSocketFactory = sslContext.getSocketFactory()
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }
            builder.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}