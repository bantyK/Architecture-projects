package banty.com.daggerrx.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import banty.com.daggerrx.models.Cryptocurrency

@Database(entities = arrayOf(Cryptocurrency::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cryptocurrenciesDao(): CryptocurrencyDao
}