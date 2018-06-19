package banty.com.daggerrx.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import banty.com.daggerrx.R
import banty.com.daggerrx.data.Cryptocurrency
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

class CryptocurrenciesActivity : AppCompatActivity() {

    @Inject
    lateinit var cryptocurrenciesViewModelFactory: CryptocurrenciesViewModelFactory
    lateinit var cryptocurrenciesViewModel: CryptocurrencyViewModel

    var cryptocurrenciesAdapter = CryptocurrencyAdapter(ArrayList())
    var currentPage = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)
        cryptocurrenciesViewModel = ViewModelProviders.of(this, cryptocurrenciesViewModelFactory).get(
                CryptocurrencyViewModel::class.java)
        initialiseRecyclerView()

        AndroidInjection.inject(this)

        progressBar.visibility = View.VISIBLE
        loadData()

        cryptocurrenciesViewModel.cryptocurrecyResult().observe(this,
                Observer<List<Cryptocurrency>> {
                    if (it != null) {
                        val position = cryptocurrenciesAdapter.itemCount
                        cryptocurrenciesAdapter.addCryptocurrency(it)
                        recycler.adapter = cryptocurrenciesAdapter
                        recycler.scrollToPosition(position - 12)
                    }
                })

        cryptocurrenciesViewModel.cryptocurrenciesError().observe(this, Observer<String> {
            if (it != null) {
                Toast.makeText(this, resources.getString(R.string.cryptocurrency_error_message) + it,
                        Toast.LENGTH_SHORT).show()
            }
        })

        cryptocurrenciesViewModel.cryptocurrenciesLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })
    }

    private fun initialiseRecyclerView() {
        val gridLayoutManager = GridLayoutManager(this, 1)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL

        recycler.apply {
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
            addOnScrollListener(InfiniteScroller({ loadData() }, gridLayoutManager))
        }
    }

    private fun loadData() {
        cryptocurrenciesViewModel.loadCryptocurrencies(12, 12)
        currentPage++
    }

    override fun onDestroy() {
        super.onDestroy()
        cryptocurrenciesViewModel.disposeObserver()
    }
}
