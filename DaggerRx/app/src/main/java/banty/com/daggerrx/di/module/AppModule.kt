package banty.com.daggerrx.di.module

import android.arch.persistence.room.Room
import banty.com.daggerrx.CryptoApp
import banty.com.daggerrx.data.source.local.CryptocurrencyDao
import banty.com.daggerrx.data.source.local.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: CryptoApp) {

    @Provides
    @Singleton
    fun provideApplication(): CryptoApp = app

    @Provides
    @Singleton
    fun provideCryptocurrenciesDatabase(app: CryptoApp): Database =
            Room.databaseBuilder(app, Database::class.java, "cryptocurrencies_db").build()

    @Provides
    @Singleton
    fun provideCryptocurrenciesDao(database: Database): CryptocurrencyDao = database.cryptocurrenciesDao()
}