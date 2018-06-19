package banty.com.daggerrx.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import banty.com.daggerrx.data.Cryptocurrency
import banty.com.daggerrx.data.source.CryptocurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by Banty on 16/06/18.
 */
class CryptocurrencyViewModel @Inject constructor(private val cryptocurrencyRepository: CryptocurrencyRepository) : ViewModel() {

    val cryptocurrenciesResult: MutableLiveData<List<Cryptocurrency>> = MutableLiveData()
    val cryptocurrenciesError: MutableLiveData<String> = MutableLiveData()
    var cryptocurrenciesLoader: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<List<Cryptocurrency>>

    fun cryptocurrecyResult(): LiveData<List<Cryptocurrency>> {
        return cryptocurrenciesResult
    }

    fun cryptocurrenciesError(): LiveData<String> {
        return cryptocurrenciesError
    }

    fun cryptocurrenciesLoader(): LiveData<Boolean> {
        return cryptocurrenciesLoader
    }

    fun loadCryptocurrencies(limit: Int, offset: Int) {
        disposableObserver = object : DisposableObserver<List<Cryptocurrency>>() {
            override fun onComplete() {

            }

            override fun onNext(cryptocurrencies: List<Cryptocurrency>) {
                cryptocurrenciesResult.postValue(cryptocurrencies)
                cryptocurrenciesLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                cryptocurrenciesError.postValue(e.message)
                cryptocurrenciesLoader.postValue(false)
            }

        }

        cryptocurrencyRepository.getCryptocurrencies(limit, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeObserver() {
        if (!disposableObserver.isDisposed)
            disposableObserver.dispose()

    }


}