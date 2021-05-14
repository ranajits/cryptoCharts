package com.ranajit.tredzerv.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
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
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException
import java.nio.charset.StandardCharsets


class MainActivity : AppCompatActivity() {
    var bankNameRecyclerAdapter: ChartsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* lineChart.gradientFillColors =
            intArrayOf(
                Color.parseColor("#81000000"),
                Color.TRANSPARENT
            )
        lineChart.animation.duration = animationDuration
        lineChart.tooltip =
            SliderTooltip().also {
                it.color = Color.RED
            }
        lineChart.onDataPointTouchListener = { index, _, _ ->
            Toast.makeText(
                this, "" + lineSet.toList()[index]
                    .second
                    .toString(), Toast.LENGTH_SHORT
            ).show()
            *//*lineChartValue.text =
                lineSet.toList()[index]
                    .second
                    .toString()*//*
        }
        lineChart.axis.shouldDisplayAxisY()

        val list= ArrayList<Label>()
        val label= Label("cdfdfd", 0f, 2f)
        list.add(label)
        *//*list.add(label)
        list.add(label)
        list.add(label)
        list.add(label)
        list.add(label)*//*
        //lineChart.drawLabels(list)

        lineChart.animate(lineSet)
*/


        val response = Gson().fromJson(loadJSONFromAsserts("CurrencyResponse.json"), CurrencyResponse::class.java)
        setupList(response)

    }

    private fun setupList(response: CurrencyResponse){
        //val response = Gson().fromJson(toString, CurrencyResponse::class.java)



        response.message?.let { Log.e("messssssage", it) }

        val arrayList = ArrayList<List<Pair<String, Float>>>()
        arrayList.add(lineSet)
        arrayList.add(lineSet1)
        arrayList.add(lineSet2)
        arrayList.add(lineSet3)

        for ((i, data) in response.data?.portfolios!!.withIndex()){
            data?.chartData = arrayList[i]
        }

        response.data.portfolios[0]?.let { setupMainChart(it) }

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



    }

    private fun loadJSONFromAsserts(fileName: String?): String? {
        var json: String? = null
        json = try {
            val `is` = assets.open(fileName!!)
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }


    companion object {

        private val lineSet = listOf(
                "label1" to 1f,
                "label2" to 4.5f,
                "label3" to 4.7f,
                "label4" to 3.5f,
                "label5" to 3.6f,
                "label6" to 7.5f,
                "label\n7" to 12.5f,
                "label\n8888" to 15f
        )
        private val lineSet1 = listOf(
                "label1" to 5f,
                "label2" to 4.5f,
                "label3" to 4.7f,
                "label4" to 3.5f,
                "label5" to 3.6f,
                "label6" to 7.5f,
                "label7" to 7.5f,
                "label12" to 4f
        )
        private val lineSet2 = listOf(

                "label5" to 3.6f,
                "label6" to 7.5f,
                "label7" to 7.5f,
                "label8" to 10f,
                "label9" to 5f,
                "label10" to 6.5f,
                "label11" to 3f,
                "label12" to 4f
        )
        private val lineSet3 = listOf(
                "label1" to 5f,
                "label2" to 4.5f,
                "label3" to 4.7f,
                "label4" to 3.5f,
                "label9" to 5f,
                "label10" to 6.5f,
                "label11" to 3f,
                "label12" to 4f
        )


        private const val animationDuration = 1000L
    }

    fun setupMainChart(portfolio: CurrencyResponse.Data.Portfolio){
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
            Toast.makeText(
                    this, "" + lineSet.toList()[index]
                    .second
                    .toString(), Toast.LENGTH_SHORT
            ).show()

        }
        lineChart.axis.shouldDisplayAxisY()

        lineChart.animate(portfolio.chartData)
    }
}