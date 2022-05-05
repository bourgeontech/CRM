package com.git.crm.Leads

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.socialmarketing.Pagination.OnLoadMoreListener
import com.git.crm.Adapter.MainLeadAdapter
import com.git.crm.CustomerDetails.CustomerDetailsOneFragment
import com.git.crm.EditLeadOne.EditLeadOneFragment

import com.git.crm.MainActivity
import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.ViewLead
import com.git.crm.ProfileLead.ProfileLeadFragment
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import com.git.crm.ViewLead.ViewLeadFragment
import com.git.greenexvendor.Pagination.RecyclerViewLoadMoreScroll
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.fragment_leads.*
import kotlinx.android.synthetic.main.fragment_leads.ivback
import kotlinx.android.synthetic.main.fragment_leads.progress
import java.text.SimpleDateFormat
import java.util.*

class LeadsFragment : Fragment(), LeadView {
    private lateinit var adapter: MainLeadAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: LinearLayoutManager
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = LeadPresenter(this, LeadInteractor())
    public var position: Int = 0
    private lateinit var lead_interface: MainLeadAdapter.orderSelectionListener
    private lateinit var datetype: String
    private var date: String = ""
    var cal = Calendar.getInstance()
    private var date2: String = ""
    var cal2 = Calendar.getInstance()
    private var sendFormat: String = ""
    private var sendFormat2: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_leads, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, null)
        (activity as MainActivity?)!!.setVisibility("")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(userId!!, "1", "", "", "added", "0", "10", "", datetype)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        (activity as MainActivity?)!!.heading("New Lead")
        flAdd.visibility = View.VISIBLE
        lead_interface = object : MainLeadAdapter.orderSelectionListener {
            override fun onOrderselection(
                leadid: String,
                heading: String,
                remark: String,
                date: String,
                type: String
            ) {


//                    var confirmfragment =
//                        ProfileLeadFragment.newInstance(
//                            leadid!!, heading!!, remark!! ,date!!,date
//                        )
//                    fragmentManager?.beginTransaction()
//                        ?.replace(
//                            R.id.fl_container,
//                            confirmfragment,
//                            ProfileLeadFragment.javaClass.name
//                        )
//                        ?.addToBackStack("")?.commit()

                getBottomSheet(
                    leadid,
                    heading,
                    remark,
                    date,
                    type
                )


            }

        }
        flAdd.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, CustomerDetailsOneFragment())?.addToBackStack("")
                ?.commitAllowingStateLoss()

        }
        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        val calendar: Calendar = Calendar.getInstance()
        val mdformat2 = SimpleDateFormat("dd MMM yyyy")
        val mdformat3 = SimpleDateFormat("dd MM yyyy")
        val strDate1 = mdformat2.format(calendar.getTime())
        val strDate2 = mdformat3.format(calendar.getTime())
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal2.set(Calendar.YEAR, year)
                cal2.set(Calendar.MONTH, monthOfYear)
                cal2.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView2()
            }
        }
        tvFrom.setOnClickListener {
            val mDatePicker = DatePickerDialog(
                requireActivity()!!,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            // mDatePicker.datePicker.minDate=System.currentTimeMillis()-1000;
            mDatePicker.show()
        }

        tvTo.setOnClickListener {
            val mDatePicker2 = DatePickerDialog(
                requireActivity()!!,
                dateSetListener2,
                // set DatePickerDialog to point to today's date when it loads up
                cal2.get(Calendar.YEAR),
                cal2.get(Calendar.MONTH),
                cal2.get(Calendar.DAY_OF_MONTH)
            )
            // mDatePicker.datePicker.minDate=System.currentTimeMillis()-1000;
            mDatePicker2.show()
        }
        tvSearch.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(
                    userId!!,
                    "1",
                    date,
                    date2,
                    "added",
                    "0",
                    "10",
                    edtSearch.text.toString(), datetype
                )
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()

        }
        ivAdd.visibility = View.VISIBLE
        ivAdd.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fl_container, CustomerDetailsOneFragment())?.addToBackStack("")
                ?.commitAllowingStateLoss()

        }
    }

    private fun updateDateInView() {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        tvFrom!!.text = sdf.format(cal.getTime())
        sendFormat = "yyyy-MM-dd" // mention the format you need
        val sdf1 = SimpleDateFormat(sendFormat, Locale.US)
        date = sdf1.format(cal.getTime())

    }

    private fun updateDateInView2() {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        tvTo!!.text = sdf.format(cal2.getTime())
        sendFormat2 = "yyyy-MM-dd" // mention the format you need
        val sdf1 = SimpleDateFormat(sendFormat, Locale.US)
        date2 = sdf1.format(cal2.getTime())
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

    override fun Lead(lead: MutableList<GetLeadsPojo?>) {
        try {
            rvLeads.adapter = null
            rvLeads?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = MainLeadAdapter(
                lead,
                requireActivity(),
                requireFragmentManager(), lead_interface

            )
            rvLeads?.adapter = adapter

            setRVLayoutManager()
            setRVScrollListener()
        } catch (e: java.lang.Exception) {
        }
    }

    private fun setRVLayoutManager() {
        mLayoutManager = LinearLayoutManager(activity)
        rvLeads?.layoutManager = mLayoutManager
        rvLeads?.setHasFixedSize(true)
    }

    private fun setRVScrollListener() {
        mLayoutManager = LinearLayoutManager(activity)
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                println("working..........")
                LoadMoreData()
            }
        })
        rvLeads?.addOnScrollListener(scrollListener)
    }

    private fun LoadMoreData() {
        // adapter?.addLoadingView()
        //loadMoreItemsCells = ArrayList()
        val start = ++position;
        presenter.leadmore(
            userId!!,
            "1",
            date,
            date2,
            "added",
            start.toString(),
            "10",
            edtSearch.text.toString()
        )
    }

    override fun loginError(msg: String) {
        rvLeads.adapter = null
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun LeadMore(lead: MutableList<GetLeadsPojo?>) {
        try {
            //Toast.makeText(activity!!, "Successfully posted", Toast.LENGTH_LONG).show()
            //adapter?.removeLoadingView()
            adapter?.addData(lead)
            scrollListener.setLoaded()
            rvLeads.post {
                adapter?.notifyDataSetChanged()
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun leadDetails(mydata: ViewLead) {
        if (mydata != null) {
            var confirmfragment =
                EditLeadOneFragment.newInstance(
                    mydata
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    EditLeadOneFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
        }
    }

    private fun getBottomSheet(
        leadid: String,
        heading: String,
        remark: String,
        date: String,
        type: String
    ) {
        val bottomSheetDialog = BottomSheetDialog(requireActivity())
        bottomSheetDialog.setContentView(com.git.crm.R.layout.bottom_menu)


        val tvView = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvView)
        val tvEdit = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvEdit)
        val tvEditLead = bottomSheetDialog.findViewById<TextView>(com.git.crm.R.id.tvEditLead)
        bottomSheetDialog.show()
        tvView?.setOnClickListener {
            var confirmfragment =
                ViewLeadFragment.newInstance(
                    leadid!!
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    ViewLeadFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
            bottomSheetDialog.dismiss()
            bottomSheetDialog.dismiss()
        }
        tvEdit?.setOnClickListener {
            var confirmfragment =
                ProfileLeadFragment.newInstance(
                    leadid!!, heading!!, remark!!, date!!, date
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    ProfileLeadFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
            bottomSheetDialog.dismiss()
        }
        tvEditLead?.setOnClickListener {
            presenter.leaddetails(userId!!, leadid!!)
            bottomSheetDialog.dismiss()
        }
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String) =
            LeadsFragment().apply {
                arguments = Bundle().apply {
                    datetype = param1
                }
            }
    }
}