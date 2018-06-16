package banty.com.daggerrx.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by Banty on 16/06/18.
 */
class CryptocurrenciesViewModelFactory @Inject constructor(private val cryptocurrencyViewModel: CryptocurrencyViewModel)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptocurrencyViewModel::class.java)) {
            return cryptocurrencyViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")

    }

}