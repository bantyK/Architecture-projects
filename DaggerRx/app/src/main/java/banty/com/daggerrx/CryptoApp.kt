package banty.com.daggerrx

import android.app.Activity
import android.app.Application
import banty.com.daggerrx.di.component.DaggerAppComponent
import banty.com.daggerrx.di.module.AppModule
import banty.com.daggerrx.di.module.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoApp : Application(), HasActivityInjector {

    private val BASE_URL = "https://api.coinmarketcap.com/v1/"

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(BASE_URL))
                .build()
                .inject(this)

    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
