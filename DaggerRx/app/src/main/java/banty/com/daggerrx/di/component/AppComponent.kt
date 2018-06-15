package banty.com.daggerrx.di.component

import banty.com.daggerrx.CryptoApp
import banty.com.daggerrx.di.module.AppModule
import banty.com.daggerrx.di.module.BuildersModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class))
interface AppComponent {

    fun inject(app: CryptoApp)
}