package com.git.crm.Interested

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.socialmarketing.Pagination.OnLoadMoreListener
import com.git.crm.Adapter.LeadAdapter
import com.git.crm.MainActivity
import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.ProfileLead.ProfileLeadFragment
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import com.git.greenexvendor.Pagination.RecyclerViewLoadMoreScroll
import kotlinx.android.synthetic.main.fragment_leads.*
import java.text.SimpleDateFormat
import java.util.*

class InterestedLeadsFragment : Fragment(), InterestedLeadView {
    private lateinit var lead_interface: LeadAdapter.orderSelectionListener
    private lateinit var adapter: LeadAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: LinearLayoutManager
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = InterestedLeadPresenter(this, InterestedLeadInteractor())
    public var position: Int = 0
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
        (activity as MainActivity?)!!.heading("Interested")
        (activity as MainActivity?)!!.setVisibility("")
        userId = preferenceObj.getStringValue(Constants.PRES_ID, null)
        try {

            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(
                    userId!!,
                    "8",
                    date,
                    date2,
                    "added",
                    "0",
                    "10",
                    edtSearch.text.toString()
                )
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        lead_interface = object : LeadAdapter.orderSelectionListener {
            override fun onOrderselection(
                leadid: String,
                heading: String,
                remark: String,
                date: String,
                type: String
            ) {


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


//                if(type.equals("save"))
//                {
//                    presenter.savefollow(userId!!,leadid,heading,remark,date)
//                }else
//                {
//                    presenter.deletefollow(leadid)
//
//                }
            }

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
                    "2",
                    date,
                    date2,
                    "added",
                    "0",
                    "10",
                    edtSearch.text.toString()
                )
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()

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
            adapter = LeadAdapter(
                lead,
                requireActivity(),
                requireFragmentManager(),
                lead_interface
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
            "2",
            date,
            date2,
            "added",
            start.toString(),
            "10",
            edtSearch.text.toString()
        )
    }

    override fun loginError(msg: String) {
        try {

            rvLeads.adapter = null
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
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


}