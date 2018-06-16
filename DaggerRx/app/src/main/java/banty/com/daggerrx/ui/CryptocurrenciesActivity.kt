package banty.com.daggerrx.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import banty.com.daggerrx.R
import banty.com.daggerrx.data.Cryptocurrency
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class CryptocurrenciesActivity : AppCompatActivity() {

    @Inject
    lateinit var cryptocurrenciesViewModelFactory: CryptocurrenciesViewModelFactory
    lateinit var cryptocurrencyViewModel: CryptocurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        AndroidInjection.inject(this)

        cryptocurrencyViewModel = ViewModelProviders.of(this, cryptocurrenciesViewModelFactory)
                .get(CryptocurrencyViewModel::class.java)

        cryptocurrencyViewModel.loadCryptocurrencies()

        cryptocurrencyViewModel.cryptocurrecyResult()
                .observe(this, Observer<List<Cryptocurrency>> {
                    result.text = "${it?.size} cryptocurrencies"
                })

        cryptocurrencyViewModel.cryptocurrenciesError().observe(this, Observer<String> {
            result.text = "Hello error $it"
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        cryptocurrencyViewModel.disposeObserver()
    }
}
