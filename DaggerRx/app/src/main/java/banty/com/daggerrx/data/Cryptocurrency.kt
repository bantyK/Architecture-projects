package banty.com.daggerrx.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(tableName = "cryptocurrencies")

data class Cryptocurrency(

        @Json(name = "id")
        @PrimaryKey
        val id: String,

        @Json(name = "price_usd")
        val priceUsd: String? = null,

        @Json(name = "symbol")
        val symbol: String? = null,

        @Json(name = "last_updated")
        val lastUpdated: String? = null,

        @Json(name = "total_supply")
        val totalSupply: String? = null,

        @Json(name = "24h_volume_usd")
        val jsonMember24hVolumeUsd: String? = null,

        @Json(name = "price_btc")
        val priceBtc: String? = null,

        @Json(name = "available_supply")
        val availableSupply: String? = null,

        @Json(name = "market_cap_usd")
        val marketCapUsd: String? = null,

        @Json(name = "percent_change_1h")
        val percentChange1h: String? = null,

        @Json(name = "percent_change_24h")
        val percentChange24h: String? = null,

        @Json(name = "name")
        val name: String? = null,

        @Json(name = "max_supply")
        val maxSupply: String? = null,

        @Json(name = "rank")
        val rank: String? = null,

        @Json(name = "percent_change_7d")
        val percentChange7d: String? = null
) : Serializable