package banty.com.daggerrx.api

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Banty on 15/06/18.
 */
class ApiClient {

    companion object {

        private const val BASE_URL = "https://api.coinmarketcap.com/v1/"

        fun getClient(): Retrofit {
            val okHttpClient: OkHttpClient = OkHttpClient.Builder().build()
            val moshi = Moshi.Builder().build()

            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }


    }
}