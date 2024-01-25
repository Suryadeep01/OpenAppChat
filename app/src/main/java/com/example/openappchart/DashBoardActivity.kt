package com.example.openappchart

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.openappchart.HomeViewModel.HomeViewModel
import com.example.openappchart.OpenInModel.TopLinks
import com.example.openappchart.adapter.DashBoardAdapter
import com.example.openappchart.adapter.ViewPagerAdapter
import com.example.openappchart.fragment.RecentLinkFragment
import com.example.openappchart.fragment.TopLinkFragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

import com.google.android.material.tabs.TabLayout
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Date
import java.util.Locale


class DashBoardActivity : AppCompatActivity() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var pager: ViewPager
    private lateinit var tab: TabLayout
    lateinit var recyclerView: RecyclerView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        recyclerView = findViewById<RecyclerView>(R.id.horizontal_cycle)



        //recyclerView(it)
        val greetingTextView: TextView = findViewById(R.id.greetings)

        val currentHour = LocalTime.now().hour

        val greetingMessage = when (currentHour) {
            in 0 until 12 -> "Good morning"
            in 12 until 17 -> "Good afternoon"
            else -> "Good evening"
        }

        //greetingTextView.text = greetingMessage
        viewModel.getApiLink()
        viewModel.topLinks.observe(this){
            Log.e("hidahilfhd",it.toString())
         recyclerView(it)
         linCharts(it)

        }
        pager = findViewById(R.id.viewPager)
        tab = findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(TopLinkFragment(), "TopLink")
        adapter.addFragment(RecentLinkFragment(), "RecentLink")


        pager.adapter = adapter
        tab.setupWithViewPager(pager)
    }
    private fun linCharts(topLinks: TopLinks) {
        val lineChart: LineChart = findViewById(R.id.line_chart)
        // Create sample data
        val overallUrlChart: Map<String, Double> = topLinks.data.overall_url_chart
        val jsonString = topLinks.data.overall_url_chart
        val entries = mutableListOf<Entry>()
         overallUrlChart.forEach { t, u ->
             val formattedDate = convertDateFormat(t)
             entries.add(Entry(formattedDate.date.toFloat(), u.toFloat()))

         }


        // Create LineDataSet
        val dataSet = LineDataSet(entries, "Sample Data")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK

        // Create LineData and set it to the chart
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Customize chart
        val description = Description()
        description.text = "Sample Line Chart"
        lineChart.description = description

        // Refresh chart
        lineChart.invalidate()
    }
    private fun convertDateFormat(inputDate: String): Date {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return parser.parse(inputDate) ?: Date()
    }

    private fun recyclerView(topLinks: TopLinks) {
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val adapter= DashBoardAdapter(topLinks)
        recyclerView.adapter = adapter
    }
}