package com.ranajit.tredzerv.ui

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.db.williamchart.data.shouldDisplayAxisY
import com.google.gson.Gson
import com.ranajit.tredzerv.R
import com.ranajit.tredzerv.adapter.ChartsAdapter
import com.ranajit.tredzerv.model.CurrencyResponse
import com.ranajit.tredzerv.utils.Util
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Ranajit on 13,May,2021
 */
class MainActivity : AppCompatActivity() {
    var bankNameRecyclerAdapter: ChartsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val response = Gson().fromJson(Util.loadJSONFromAsserts(this, "CurrencyResponse.json"), CurrencyResponse::class.java)
        setupList(response)
        txt_see_all.setOnClickListener {
            Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupList(response: CurrencyResponse) {
        val arrayList = ArrayList<List<Pair<String, Float>>>()
        arrayList.add(lineSet)
        arrayList.add(lineSet1)
        arrayList.add(lineSet2)
        arrayList.add(lineSet3)

        for ((i, data) in response.data?.portfolios!!.withIndex()) {
            data?.chartData = arrayList[i]
        }

        ChartsAdapter.selectedPosition = 0
        if (bankNameRecyclerAdapter == null) {
            bankNameRecyclerAdapter = ChartsAdapter(response.data.portfolios, this@MainActivity)
            rv_currencies.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            val itemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            ContextCompat.getDrawable(this, R.drawable.divider)?.let { itemDecoration.setDrawable(it) }
            rv_currencies.addItemDecoration(itemDecoration)
            rv_currencies.adapter = bankNameRecyclerAdapter
        } else {
            bankNameRecyclerAdapter!!.updateData(response.data.portfolios)
        }

        response.data.portfolios[0]?.let { setupMainChart(it) }

    }


    companion object {

        private val lineSet = listOf(
                "Mon" to 10f,
                "Tue" to 40.5f,
                "Wed" to 45.7f,
                "Thu" to 33.5f,
                "Fri" to 40.6f,
                "Sat" to 30.5f,
                "Sun" to 52.5f,
                "Mon" to 15f
        )
        private val lineSet1 = listOf(

                "Mon" to 153f,
                "Tue" to 64.5f,
                "Wed" to 40.72f,
                "Thu" to 73.5f,
                "Fri" to 30.6f,
                "Sat" to 75.5f,
                "Sun" to 32.5f,
                "Mon" to 15f

        )
        private val lineSet2 = listOf(


                "Mon" to 150f,
                "Tue" to 40.5f,
                "Wed" to 140.7f,
                "Thu" to 70.5f,
                "Fri" to 50.6f,
                "Sat" to 70.5f,
                "Sun" to 120.5f,
                "Mon" to 150f
        )
        private val lineSet3 = listOf(
                "Mon" to 50f,
                "Tue" to 40.5f,
                "Wed" to 64.7f,
                "Thu" to 43.5f,
                "Fri" to 50f,
                "Sat" to 60.5f,
                "Sun" to 30f,
                "Mon" to 40f
        )


        private const val animationDuration = 1000L
    }

    fun setupMainChart(portfolio: CurrencyResponse.Data.Portfolio) {
        txt_card_title.text = portfolio.currencyName
        txt_card_desc.text = portfolio.sharePercentage
        txt_card_amt_title.text = portfolio.currencyToDoller
        txt_card_amt_desc.text = portfolio.currencyValue

        Glide.with(this).load(portfolio?.logo).into(img_logo)
        lineChart.gradientFillColors =
                intArrayOf(
                        Color.parseColor("#81000000"),
                        Color.TRANSPARENT
                )
        lineChart.animation.duration = animationDuration
        lineChart.onDataPointTouchListener = { index, _, _ ->
            /*Toast.makeText(
                    this, "" + lineSet.toList()[index]
                    .second
                    .toString(), Toast.LENGTH_SHORT
            ).show()*/

        }

        lineChart.lineColor = Color.parseColor(portfolio?.chartLineColor)
        lineChart.gradientFillColors =
                intArrayOf(
                        Color.parseColor(portfolio?.chartShadowColor),
                        Color.TRANSPARENT
                )

        lineChart.axis.shouldDisplayAxisY()

        lineChart.animate(portfolio.chartData)
    }
}