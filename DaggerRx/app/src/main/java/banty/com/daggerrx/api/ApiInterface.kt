package banty.com.daggerrx.api

import banty.com.daggerrx.models.Cryptocurrency
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