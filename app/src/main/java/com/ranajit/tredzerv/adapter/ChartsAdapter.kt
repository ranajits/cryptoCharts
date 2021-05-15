package com.ranajit.tredzerv.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.db.williamchart.view.LineChartView
import com.ranajit.tredzerv.R
import com.ranajit.tredzerv.model.CurrencyResponse.Data.Portfolio
import com.ranajit.tredzerv.ui.portfolio.PortfolioActivity
import org.json.JSONException

/**
 * Created by Ranajit on 14,May,2021
 */
class ChartsAdapter(private var currencyList: ArrayList<Portfolio?>?, private val context: Context) : RecyclerView.Adapter<ChartsAdapter.ChartsViewHolder>() {
    private  val animationDuration = 1000L
    private val currencyInterface: PortfolioActivity = context as PortfolioActivity

    fun updateData(dataJsonArray: ArrayList<Portfolio?>?) {
        currencyList = dataJsonArray
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartsViewHolder {
        val currencyListRow: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_currency, parent, false)
        return ChartsViewHolder(currencyListRow)
    }

    override fun onBindViewHolder(holder: ChartsViewHolder, position: Int) {
        try {
            val currencyObject = currencyList?.get(position)
            holder.txt_card_title.text = currencyObject?.currencyName
            holder.txt_card_desc.text = currencyObject?.sharePercentage
            holder.txt_card_amt_title.text = currencyObject?.currencyToDoller
            holder.txt_card_amt_desc.text = currencyObject?.currencyValue
            Glide.with(context).load(currencyObject?.logo).into(holder.imageView)

            holder.lineChart.lineColor = Color.parseColor(currencyObject?.chartLineColor)
            holder.lineChart.gradientFillColors =
                intArrayOf(
                    Color.parseColor(currencyObject?.chartShadowColor),
                    Color.TRANSPARENT
                )
            holder.lineChart.animation.duration = animationDuration
            currencyList?.get(position)?.let { holder.lineChart.animate(it.chartData) }


        } catch (e: Exception) {
            Log.e("Exception", "",e)
        }
    }

    override fun getItemCount(): Int {
        return currencyList!!.size
    }


    inner class ChartsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var main_ll: ConstraintLayout
        var lineChart: LineChartView
        var txt_card_title: TextView
        var txt_card_desc: TextView
        var txt_card_amt_title: TextView
        var txt_card_amt_desc: TextView
        var imageView: ImageView

        init {
            txt_card_title = view.findViewById(R.id.txt_card_title)
            txt_card_desc = view.findViewById(R.id.txt_card_desc)
            txt_card_amt_title = view.findViewById(R.id.txt_card_amt_title)
            txt_card_amt_desc = view.findViewById(R.id.txt_card_amt_desc)
            main_ll = view.findViewById(R.id.main_ll)
            lineChart = view.findViewById(R.id.lineChart)
            imageView = view.findViewById(R.id.img_logo)
            val clickListener =
                View.OnClickListener {
                    try {
                        selectedPosition = adapterPosition
                        currencyList?.get(selectedPosition)?.let { it1 ->
                            currencyInterface.setupMainChart(
                                it1
                            )
                        }
                      //  notifyDataSetChanged()
                    } catch (e: JSONException) {
                        Log.e("Exception", "", e)
                    }
                }
            main_ll.setOnClickListener(clickListener)
        }
    }

    companion object {
        var selectedPosition = -1
    }

}