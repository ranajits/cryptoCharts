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
import com.ranajit.tredzerv.ui.MainActivity
import org.json.JSONException
import org.json.JSONObject

class ChartsAdapter(private var bankList: ArrayList<Portfolio?>?, private val context: Context) : RecyclerView.Adapter<ChartsAdapter.ChartsViewHolder>() {
    private  val animationDuration = 1000L
    private val bankInterface: MainActivity

    fun updateData(dataJsonArray: ArrayList<Portfolio?>?) {
        bankList = dataJsonArray
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChartsViewHolder {
        val bankListRow: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item_currency, parent, false)
        return ChartsViewHolder(bankListRow)
    }

    override fun onBindViewHolder(holder: ChartsViewHolder, position: Int) {
        try {
            val bankObject = bankList?.get(position)
            holder.txt_card_title.text = bankObject?.currencyName
            holder.txt_card_desc.text = bankObject?.sharePercentage
            holder.txt_card_amt_title.text = bankObject?.currencyToDoller
            holder.txt_card_amt_desc.text = bankObject?.currencyValue
            if (selectedPosition == position) {
                holder.main_ll.setBackgroundColor(context.resources.getColor(R.color.white))
             //   holder.bank_name_tv.setTextColor(
//                    context.resources.getColor(R.color.material_grey_800)
//                )
            } else {
                holder.main_ll.setBackgroundColor(context.resources.getColor(R.color.white))
//                holder.bank_name_tv.setTextColor(
//                    context.resources.getColor(R.color.material_grey_800)
//                )
            }

            Glide.with(context).load(bankObject?.logo).into(holder.imageView)





            holder.lineChart.gradientFillColors =
                intArrayOf(
                    Color.parseColor("#81000000"),
                    Color.TRANSPARENT
                )
            holder.lineChart.animation.duration = animationDuration

            holder.lineChart.onDataPointTouchListener = { index, _, _ ->
//                Toast.makeText(
//                    this, "" + bankList.get(position).toList()[index]
//                        .second
//                        .toString(), Toast.LENGTH_SHORT
//                ).show()
                /*lineChartValue.text =
                    lineSet.toList()[index]
                        .second
                        .toString()*/
            }
            bankList?.get(position)?.let { holder.lineChart.animate(it.chartData) }


        } catch (e: Exception) {
           // LogUtils.logE("Exception", e)
        }
    }

    override fun getItemCount(): Int {
        return bankList!!.size
    }

    interface BankInterface {
        fun setData(selectedData: JSONObject?)
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
                        bankList?.get(selectedPosition)?.let { it1 ->
                            bankInterface.setupMainChart(
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

    init {
        bankInterface = context as MainActivity
    }
}