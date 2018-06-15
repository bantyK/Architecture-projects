package banty.com.daggerrx.di.module

import banty.com.daggerrx.CryptocurrenciesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptocurrenciesActivity(): CryptocurrenciesActivity
}
