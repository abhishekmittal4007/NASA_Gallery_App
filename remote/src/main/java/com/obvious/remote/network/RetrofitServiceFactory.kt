package com.obvious.remote.network

import android.content.Context
import com.obvious.remote.Host
import java.io.IOException
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A Factory class for generating retrofit client Use <b>[get][RetrofitServiceFactory.get]</b> to
 * receive a retrofit client.
 *
 * <b>Sample usage</b>
 * ```
 * RetrofitServiceFactory.get(Host.RETROFIT).create(Api::class.java)
 * ```
 *
 * @receiver [SharedPreferences]
 *
 * receiver
 * @see SharedPreferences.editAndApply
 */
class RetrofitServiceFactory {
  companion object {

    private lateinit var retrofitClient: Retrofit
    private val TIMEOUT_THRESHOLD = 30L

    private var BASE_URL = "https://www.sportskeeda.com/"
    private const val TIMEOUT = 8L

    /**
     * Method to get Retrofit client based on the URL type
     *
     * @param[customAction] Custom action to be executed on the created [editor]
     * [SharedPreferences.Editor]
     */
    fun get(host: String, context: Context): Retrofit {
      return when (host) {
        Host.API_DOMAIN -> {
          if (!this::retrofitClient.isInitialized) {
            retrofitClient = getRetrofit(Host.API_DOMAIN, context = context)
          }
          return retrofitClient
        }
        else -> {
          return getRetrofit(BASE_URL, context)
        }
      }
    }

    private fun getRetrofit(host: String, context: Context): Retrofit {
      val builder = Retrofit.Builder()

      val httpClient = OkHttpClient.Builder()

      // add interceptors
      // set your desired log level
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      httpClient.addInterceptor(logging)

      // Set timeouts
      httpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS)
      httpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS)

      return builder
          .addCallAdapterFactory(NetworkResponseAdapterFactory())
          .addConverterFactory(GsonConverterFactory.create())
          .baseUrl(host)
          .client(httpClient.build())
          .build()
    }
  }
}
