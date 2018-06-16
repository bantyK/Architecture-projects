package banty.com.daggerrx.data.source.remote

import banty.com.daggerrx.data.Cryptocurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Banty on 15/06/18.
 */
interface ApiInterface {
    @GET("ticker/")
    fun getCryptocurrencies(@Query("start") start: String): Observable<List<Cryptocurrency>>

}