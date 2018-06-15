package banty.com.daggerrx

import android.app.Activity
import android.app.Application
import banty.com.daggerrx.di.component.DaggerAppComponent
import banty.com.daggerrx.di.module.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class CryptoApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
                .inject(this)


    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
