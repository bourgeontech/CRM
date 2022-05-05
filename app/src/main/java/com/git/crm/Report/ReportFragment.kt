package com.git.crm.Report

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.git.crm.MainActivity
import com.git.crm.Pojo.SalesReportList
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper

import kotlinx.android.synthetic.main.fragment_report.*
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.model.PieChartData
import kotlin.concurrent.fixedRateTimer


class ReportFragment : Fragment(), ReportView {


    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = ReportPresenter(this, ReportInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_report, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        (activity as MainActivity?)!!.setVisibility("")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.report(userId!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        (activity as MainActivity?)!!.heading("Report")
        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
    }

    override fun showProgress() {
        try {
            if (activity != null)
                progress.visibility = View.VISIBLE
        } catch (e: Exception) {

        }
    }

    override fun hideProgress() {
        try {
            if (activity != null)
                progress.visibility = View.GONE
        } catch (e: Exception) {

        }
    }

    override fun report(id: List<SalesReportList>?) {
        val color: MutableList<Int> = ArrayList()
        color.add(Color.RED)
        color.add(Color.BLUE)
        color.add(Color.GREEN)
        color.add(Color.YELLOW)
        color.add(Color.CYAN)
        color.add(Color.DKGRAY)
        color.add(Color.MAGENTA)
        color.add(Color.LTGRAY)
        color.add(Color.GRAY)
        color.add(Color.BLACK)
        val pieData: MutableList<SliceValue> = ArrayList()
        for (i in 0 until id?.size!!) {
            var a = id.get(i).totalSale!!
            var b = a.toFloat()
            pieData.add(
                SliceValue(
                    b,
                    color.get(i)
                ).setLabel(id.get(i).make + " Total Sale : " + id.get(i).totalSale)
            )

        }

        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true).setValueLabelTextSize(10);
        chart.setPieChartData(pieChartData);
    }

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: java.lang.Exception) {

        }
    }

}