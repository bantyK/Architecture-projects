package banty.com.daggerrx.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import banty.com.daggerrx.R
import banty.com.daggerrx.data.Cryptocurrency

/**
 * Created by Banty on 16/06/18.
 */
class CryptocurrencyAdapter(cryptocurrencies: List<Cryptocurrency>?) :
        RecyclerView.Adapter<CryptocurrencyAdapter.CryptocurrencyViewHolder>() {

    private var cryptocurrenciesList = ArrayList<Cryptocurrency>()

    init {
        this.cryptocurrenciesList = cryptocurrencies as ArrayList<Cryptocurrency>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        val itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.cryptocurrency_list_item, parent, false)
        return CryptocurrencyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return cryptocurrenciesList?.size
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        val cryptocurrencyItem = cryptocurrenciesList[position]
        holder?.cryptocurrencyListItem(cryptocurrencyItem)
    }

    fun addCryptocurrency(cryptocurrencies: List<Cryptocurrency>) {
        val initPos = cryptocurrenciesList.size
        cryptocurrenciesList.addAll(cryptocurrencies)
        notifyItemRangeInserted(initPos, cryptocurrencies.size)
    }


    class CryptocurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cryptocurrencyId = itemView.findViewById<TextView>(R.id.cryptocurrency_id)

        fun cryptocurrencyListItem(cryptocurrencyItem: Cryptocurrency) {
            cryptocurrencyId.text = cryptocurrencyItem.name
        }
    }
}

