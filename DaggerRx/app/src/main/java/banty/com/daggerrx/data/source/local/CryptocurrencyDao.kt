package banty.com.daggerrx.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import banty.com.daggerrx.data.Cryptocurrency
import io.reactivex.Single

@Dao
interface CryptocurrencyDao {

    @Query("Select * from cryptocurrencies order by rank limit :limit offset :offset")
    fun queryCryptocrrencies(limit: Int, offset: Int): Single<List<Cryptocurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoCurrency(cryptocurrency: Cryptocurrency)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCryptocurrencies(cryptocurrencies: List<Cryptocurrency>)
}