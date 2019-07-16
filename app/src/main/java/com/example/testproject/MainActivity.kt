package com.example.testproject

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener
import com.bigkoo.pickerview.listener.OnOptionsSelectListener
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bigkoo.pickerview.view.OptionsPickerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.ChartHighlighter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list = mutableListOf<Entry>().apply {
            add(Entry(0f, 2f))
            add(Entry(2f, 4f))
            add(Entry(4f, 1f))
            add(Entry(6f, 8f))
            add(Entry(8f, 7f))
            add(Entry(10f, 2f))
        }

        val list2 = mutableListOf<Entry>().apply {
            add(Entry(0f, 6f))
            add(Entry(2f, 6f))
            add(Entry(4f, 6f))
            add(Entry(6f, 6f))
            add(Entry(8f, 6f))
            add(Entry(10f, 6f))
        }

        val dataSet = LineDataSet(list, "AQI Index").apply {
            color = Color.BLACK
            valueTextSize = 12f
            isHighlightEnabled = true
            setDrawHighlightIndicators(true)
            highLightColor = Color.RED
        }

        val dataSet2 = LineDataSet(list2, "Average").apply {
            color = Color.BLUE
            valueTextSize = 0f
            isHighlightEnabled = false

        }

        chart.data = LineData(dataSet, dataSet2)

        chart.setScaleEnabled(true)
        chart.legend.textSize = 14f
        chart.axisLeft.textSize = 14f
        chart.axisRight.textSize = 14f

        MyMarkerView(this, R.layout.marker_custom).apply {
            chartView = chart
            chart.marker = this
        }

        chart.xAxis.apply {
            textSize = 14f
            valueFormatter = object : ValueFormatter() {

                private val mFormat = SimpleDateFormat("HH:mm", Locale.ENGLISH)


                override fun getFormattedValue(value: Float): String {
                    val millis = TimeUnit.MINUTES.toMillis(value.toLong())
                    return mFormat.format(Date(millis))
                }
            }

        }


//        chart.setOnChartValueSelectedListener(object :OnChartValueSelectedListener{
//            override fun onNothingSelected() {
//
//            }
//
//            override fun onValueSelected(e: Entry?, h: Highlight?) {
//
//
//
////                chart.highlightValue(h, false)
//
//            }
//        })

        chart.invalidate()


    }


}
