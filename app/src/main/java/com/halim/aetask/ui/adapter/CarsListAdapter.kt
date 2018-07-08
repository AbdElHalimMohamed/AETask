package com.halim.aetask.ui.adapter

import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.halim.aetask.R
import com.halim.aetask.domain.entity.Car
import com.halim.aetask.ui.adapter.base.BaseRecyclerAdapter
import com.halim.aetask.ui.adapter.base.BaseViewHolder
import com.halim.aetask.ui.loadUrl
import kotlinx.android.synthetic.main.adapter_car_item.view.*
import java.text.NumberFormat
import java.util.*


class CarsListAdapter(cars: List<Car> = arrayListOf())
    : BaseRecyclerAdapter<Car, CarsListAdapter.CarViewHolder>(cars) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
            CarViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.adapter_car_item, parent, false))

    class CarViewHolder(view: View) : BaseViewHolder<Car>(view) {

        companion object {
            // Time in sec (5 min)
            private const val MIN_TIME_LEFT = 300
        }

        override fun bind(car: Car) {
            val timeLeft = car.auctionInfo?.durationSec ?: 0
            val priceFormat = NumberFormat.getInstance()
            priceFormat.maximumFractionDigits = 3

            setLocalizedText(car)

            itemView?.lotNum?.text = car.auctionInfo?.lotNum?.toString()
            itemView?.bidsCount?.text = car.auctionInfo?.bids?.toString()

            itemView?.price?.text = priceFormat
                    .format(car.auctionInfo?.currentPrice ?: 0)

            itemView?.timeLeft?.text = getTime(timeLeft)
            itemView?.timeLeft?.setTextColor(ContextCompat.getColor(itemView.context,
                    if (timeLeft > MIN_TIME_LEFT) {
                        R.color.text_primary
                    } else {
                        R.color.time_red
                    }))

            itemView?.carImg?.loadUrl(car.image, placeholder = R.drawable.img_temp)
        }

        private fun setLocalizedText(car: Car) {
            val local = Locale.getDefault().language
            val make: String
            val model: String
            val currency: String?

            if (local?.contains("ar", true) == true) {
                make = car.makeAr
                model = car.modelAr
                currency = car.auctionInfo?.currencyAr
            } else {
                make = car.makeEng
                model = car.modelEng
                currency = car.auctionInfo?.currencyEng
            }

            itemView?.carTitle?.text = resources.getString(R.string.car_title,
                    make, model, car.year)

            itemView?.currency?.text = currency
        }

        private fun getTime(totalSeconds: Long): String {
            val hours = totalSeconds / 3600
            val minutes = (totalSeconds % 3600) / 60
            val seconds = totalSeconds % 60

            return String.format("%02d:%02d:%02d", hours, minutes, seconds)
        }
    }
}