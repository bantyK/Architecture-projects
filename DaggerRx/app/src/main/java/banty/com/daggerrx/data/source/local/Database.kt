package banty.com.daggerrx.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import banty.com.daggerrx.data.Cryptocurrency

@Database(entities = arrayOf(Cryptocurrency::class), version = 1)
abstract class Database : RoomDatabase() {
    abstract fun cryptocurrenciesDao(): CryptocurrencyDao
}