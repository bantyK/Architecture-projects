package banty.com.daggerrx.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import banty.com.daggerrx.models.Cryptocurrency

@Dao
interface CryptocurrencyDao {

    @Query("Select * from cryptocurrencies")
    fun queryCryptocrrencies(): List<Cryptocurrency>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCryptoCurrency(cryptocurrency: Cryptocurrency)

}