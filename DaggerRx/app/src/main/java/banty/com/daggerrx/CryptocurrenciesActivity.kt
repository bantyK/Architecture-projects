package banty.com.daggerrx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import banty.com.daggerrx.api.ApiClient
import banty.com.daggerrx.api.ApiInterface
import banty.com.daggerrx.models.Cryptocurrency
import dagger.android.AndroidInjection
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class CryptocurrenciesActivity : AppCompatActivity() {

    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        showCryptocurrencies()

    }

    private fun showCryptocurrencies() {
        val cryptocurrenciesResponse = getCryptocurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())


        val disposableObserver =
                cryptocurrenciesResponse.subscribeWith(object : DisposableObserver<List<Cryptocurrency>>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: List<Cryptocurrency>) {
                        val listSize = t.size
                        Log.e("ITEMS **** ", listSize.toString())
                    }

                    override fun onError(e: Throwable) {
                        Log.e("ERROR *** ", e.message)
                    }

                })

        compositeDisposable.addAll(disposableObserver)
    }

    private fun getCryptocurrencies(): Observable<List<Cryptocurrency>> {
        val retrofit = ApiClient.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getCryptocurrencies("0")
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
