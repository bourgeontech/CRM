package com.git.crm.Dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import com.git.crm.Converted.ConvertedLeadsFragment
import com.git.crm.CustomerDetails.CustomerDetailsOneFragment
import com.git.crm.Dialogs.DashboardDialog
import com.git.crm.FollowUpLeads.FollowUpLeadsFragment
import com.git.crm.Interested.InterestedLeadsFragment
import com.git.crm.Leads.LeadsFragment
import com.git.crm.Login.LoginActivity
import com.git.crm.MainActivity
import com.git.crm.Pojo.DashboardList
import com.git.crm.R
import com.git.crm.Report.ReportFragment
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DashboardFragment : Fragment(), DashboardView {
    private var dashvalue: String? = ""
    private var datetype: String = ""
    private lateinit var dialogvalues: DashboardDialog.OnBusIdvalue
    private var userId: String? = ""
    private var userName: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val data: MutableList<String> = ArrayList<String>()
    private val presenter = DashboardPresenter(this, DashboardInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_dashboard, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        data.clear()
        data.add("Week")
        data.add("Month")
        data.add("Today")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userName = preferenceObj.getStringValue(Constants.PRES_NAME, null)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, null)
        dashvalue = preferenceObj.getStringValue(Constants.DASHBOARD, "")
        if (dashvalue.equals("")) {
            preferenceObj.setValue(Constants.DASHBOARD, "Week")
            dashvalue = "Week"
        }
        // datetype="week"
        tvSelect.text = dashvalue
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getDashboard(userId!!, dashvalue!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        (activity as MainActivity?)!!.heading(dashvalue!!)
        (activity as MainActivity?)!!.setVisibility("ok")
        (activity as MainActivity?)!!.tvwelcome.setOnClickListener {
            val classdialog = DashboardDialog(data)
            classdialog.setBusId(dialogvalues)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }

        dialogvalues = object : DashboardDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                preferenceObj.setValue(Constants.DASHBOARD, name)
                tvSelect.text = name
                datetype = name
                (activity as MainActivity?)!!.heading(name)
                presenter.getDashboard(userId!!, name)
            }

        }
        val dateFormat: DateFormat = SimpleDateFormat("MMMM")
        val date = Date()
        //Log.d("Month", dateFormat.format(date))
        tvMonth.setText(dateFormat.format(date))
        flAdd.setOnClickListener {
            data.clear()
            data.add("Week")
            data.add("Month")
            data.add("Today")
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, CustomerDetailsOneFragment())?.addToBackStack("")
                ?.commitAllowingStateLoss()

        }
        tvSelect.setOnClickListener {

            val classdialog = DashboardDialog(data)
            classdialog.setBusId(dialogvalues)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")

        }
        ivrefresh.setOnClickListener {
            logout()
        }
        //tvwelcome.text = "Welcome " + userName
        tvNew.setOnClickListener {

            var confirmfragment =
                LeadsFragment.newInstance(
                    datetype
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    LeadsFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()

        }
        tvReport.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fl_container, ReportFragment())
                ?.addToBackStack("")?.commitAllowingStateLoss()

        }
        tvFollow.setOnClickListener {
            fragmentManager?.beginTransaction()?.replace(R.id.fl_container, FollowUpLeadsFragment())
                ?.addToBackStack("")?.commitAllowingStateLoss()

        }
        tvInterested.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, InterestedLeadsFragment())?.addToBackStack("")
                ?.commitAllowingStateLoss()

        }
        tvConverted.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, ConvertedLeadsFragment())?.addToBackStack("")
                ?.commitAllowingStateLoss()

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

    override fun navigateToHome(login: List<DashboardList>?) {
        if (activity != null) {
            try {
                tvNew.text = "Leads (" + login?.get(0)?.total_leads.toString() + ")"
                tvFollow.text = "Follow Ups (" + login?.get(0)?.newFollowups.toString() + ")"
                tvInterested.text =
                    "Not Interested (" + login?.get(0)?.not_intrested.toString() + ")"
                tvConverted.text = "Converted (" + login?.get(0)?.converted.toString() + ")"
                tvReport.text = "Reports"
            } catch (e: Exception) {

            }
        }


    }

    override fun loginError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    private fun logout() {

        val alertDialog =
            AlertDialog.Builder(requireActivity()!!, R.style.MyAlertDialogTheme)
        alertDialog.setTitle("Log Out")
        alertDialog.setMessage("Are you sure want to logout?")
        alertDialog.setPositiveButton(
            HtmlCompat.fromHtml("<font color='#000'>Yes</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        ) { dialog, which ->
            val preferenceObj = PreferenceRequestHelper(activity)
            preferenceObj.clear()
            val i = Intent(activity, LoginActivity::class.java)
            startActivity(i)
        }
        alertDialog.setNegativeButton(
            HtmlCompat.fromHtml("<font color='#000'>No</font>", HtmlCompat.FROM_HTML_MODE_LEGACY)
        ) { dialog, which -> dialog.cancel() }
        alertDialog.show()
    }

}