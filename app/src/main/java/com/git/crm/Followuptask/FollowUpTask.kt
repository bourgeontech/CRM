package com.git.crm.Followuptask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.FolloUpLeadAdapter
import com.git.crm.Dialogs.HeadingDialog
import com.git.crm.MainActivity
import com.git.crm.Pojo.FollowUpList
import com.git.crm.Pojo.HeadingList
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import com.git.greenexvendor.Pagination.RecyclerViewLoadMoreScroll
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.follow_up_bottomsheet.view.*
import kotlinx.android.synthetic.main.fragment_leads_task.*
import kotlinx.android.synthetic.main.row_lead.view.tvDate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


private const val ARG_PARAM1 = "param1"

class FollowUpTask : Fragment(), FollowUpTaskView {
    private lateinit var time: String
    private lateinit var currentDateandTime: String
    private lateinit var sdf1: SimpleDateFormat
    private lateinit var d: Date
    private  lateinit var d2: Date
    private lateinit var heading_dialogvalues: HeadingDialog.OnBusIdvalue
    private lateinit var bottomSheet: View
    private lateinit var adapter: FolloUpLeadAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: LinearLayoutManager
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = FollowUpTaskPresenter(this, PollowUpTaskInteractor())
    public var position: Int = 0
    private var date: String = ""
    var cal = Calendar.getInstance()
    private var date2: String = ""
    var cal2 = Calendar.getInstance()
    private var sendFormat: String = ""
    private var sendFormat2: String = ""
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    private val timePickerDialogListener: TimePickerDialog.OnTimeSetListener =
        object : TimePickerDialog.OnTimeSetListener {
            override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {

                // logic to properly handle
                // the picked timings by user
                val formattedTime: String = when {
                    hourOfDay == 0 -> {
                        if (minute < 10) {
                            "${hourOfDay + 12}:0${minute} am"
                        } else {
                            "${hourOfDay + 12}:${minute} am"
                        }
                    }
                    hourOfDay > 12 -> {
                        if (minute < 10) {
                            "${hourOfDay - 12}:0${minute} pm"
                        } else {
                            "${hourOfDay - 12}:${minute} pm"
                        }
                    }
                    hourOfDay == 12 -> {
                        if (minute < 10) {
                            "${hourOfDay}:0${minute} pm"
                        } else {
                            "${hourOfDay}:${minute} pm"
                        }
                    }
                    else -> {
                        if (minute < 10) {
                            "${hourOfDay}:${minute} am"
                        } else {
                            "${hourOfDay}:${minute} am"
                        }
                    }
                }

                bottomSheet.tvTime.text = formattedTime
                time=formattedTime
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_leads_task, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, null)
       // val currentTime = Calendar.getInstance().time
        val sdf = SimpleDateFormat("dd-MMM-yyyy HH:mm a", Locale.getDefault())
         currentDateandTime = sdf.format(Date())


       // Toast.makeText(activity,currentTime.toString(),Toast.LENGTH_SHORT).show()


        (activity as MainActivity?)!!.setVisibility("")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(
                    userId!!,
                    param1!!,
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
        (activity as MainActivity?)!!.heading("Follow Up")



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
            // mDatePicker2.datePicker.minDate=System.currentTimeMillis()-1000;
            mDatePicker2.show()
        }
        heading_dialogvalues = object : HeadingDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                bottomSheet.edtHeading.setText(name)
            }

        }

        tvSearch.setOnClickListener {

            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(
                    userId!!,
                    "4",
                    date,
                    date2,
                    "added",
                    "0",
                    "10",
                    edtSearch.text.toString()
                )
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        flAdd.setOnClickListener {
            getBottomSheet()
        }
    }

    private fun getBottomSheet() {
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val calendar: Calendar = Calendar.getInstance()
        val mdformat2 = SimpleDateFormat("dd MMM yyyy")
        val mdformat3 = SimpleDateFormat("dd MM yyyy")
        val strDate1 = mdformat2.format(calendar.getTime())
        val strDate2 = mdformat3.format(calendar.getTime())

        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
        val formattedDate = df.format(c)

        val df1 = SimpleDateFormat("hh:mm aaa", Locale.getDefault())
        val formattedTime = df1.format(c)
        val dialog = BottomSheetDialog(requireActivity())
        bottomSheet = layoutInflater.inflate(R.layout.follow_up_bottomsheet, null)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView1(bottomSheet.tvDate)
            }
        }
        bottomSheet.tvDate?.text = formattedDate
        bottomSheet.tvTime?.text = formattedTime
        bottomSheet.edtRemark?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                try {
                    bottomSheet.tvCount.text = (bottomSheet.edtRemark.text.length).toString() + "/100"
                } catch (e: Exception) {

                }
            }
        })
        //   bottomSheetDialog.show()
        bottomSheet.edtHeading.setOnClickListener {
            presenter.getHeading()
        }
        bottomSheet.tvSave?.setOnClickListener {
//            sdf1 = SimpleDateFormat("dd-MMM-yyyy HH:mm a")
//
//            try {
//                d2 = sdf1.parse(currentDateandTime)
//            } catch (ex: ParseException) {
//                Log.v("Exception", ex.getLocalizedMessage())
//            }
//            try {
//                d = sdf1.parse(bottomSheet.tvDate?.text.toString() + " " + bottomSheet.tvTime.text.toString())
//
//               // d = sdf1.parse(date+" "+time)
//            } catch (ex: ParseException) {
//                Log.v("Exception", ex.getLocalizedMessage())
//            }
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                //Toast.makeText(activity, bottomSheet.tvDate?.text.toString() + " " + bottomSheet.tvTime.text.toString(), Toast.LENGTH_SHORT).show()
//                if(d?.after(d2)!!) {
//                    Toast.makeText(activity, bottomSheet.tvDate?.text.toString() + " " + bottomSheet.tvTime.text.toString(), Toast.LENGTH_SHORT).show()

                    presenter.savefollow(
                        userId!!,
                        param1!!,
                        bottomSheet.edtHeading?.text.toString(),
                        bottomSheet.edtRemark?.text.toString(),
                        bottomSheet.tvDate?.text.toString() + " " + bottomSheet.tvTime.text.toString()
                    )
                    dialog.dismiss()
//                }else {
//                    Toast.makeText(activity, bottomSheet.tvDate?.text.toString() + " " + bottomSheet.tvTime.text.toString(), Toast.LENGTH_SHORT).show()
//                    println("date ..1.."+d2)
//                    println("date ..1.."+d)
//                    println("date ..1.."+date+" "+time)
//                    println("date ..1.."+d)
//
//                // Toast.makeText(activity, "Please Don't Select Previous Time ", Toast.LENGTH_SHORT).show()
              //  }

            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        bottomSheet.tvDelete?.setOnClickListener {

            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.deletefollow(param1!!)
                // lead_interface.onOrderselection(salesId.toString(),bottomSheet.edtHeading?.text.toString(),bottomSheet.edtRemark?.text.toString(),bottomSheet.tvDate?.text.toString(),"delete")

                dialog.dismiss()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        bottomSheet.tvTime.setOnClickListener {
            val timePicker: TimePickerDialog = TimePickerDialog(
                // pass the Context
                activity,
                // listener to perform task
                // when time is picked
                timePickerDialogListener,
                // default hour when the time picker
                // dialog is opened
                12,
                // default minute when the time picker
                // dialog is opened
                10,
                // 24 hours time picker is
                // false (varies according to the region)
                false
            )

            // then after building the timepicker
            // dialog show the dialog to user
            timePicker.show()
        }
        bottomSheet.tvDate?.setOnClickListener {
            val mDatePicker = DatePickerDialog(
                requireContext()!!,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            )
            mDatePicker.datePicker.minDate = System.currentTimeMillis() - 1000;
            mDatePicker.show()
        }
        dialog.setContentView(bottomSheet)
        dialog.show()
    }

    private fun updateDateInView1(tvDate: TextView?) {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        tvDate!!.text = sdf.format(cal.getTime())
        sendFormat = "dd-MMM-yyyy" // mention the format you need
        val sdf1 = SimpleDateFormat(sendFormat, Locale.US)
        date = sdf1.format(cal.getTime())

    }

    private fun updateDateInView() {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        tvFrom!!.text = sdf.format(cal.getTime())
        sendFormat = "dd-MMM-yyyy" // mention the format you need
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

    override fun Lead(lead: List<FollowUpList?>) {
        try {
            rvLeads.adapter = null
            rvLeads?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = FolloUpLeadAdapter(
                lead,
                requireActivity(),
                requireFragmentManager()

            )
            rvLeads?.adapter = adapter

//            setRVLayoutManager()
//            setRVScrollListener()
        } catch (e: java.lang.Exception) {
        }
    }

    //    private fun setRVLayoutManager() {
//        mLayoutManager = LinearLayoutManager(activity)
//        rvLeads?.layoutManager = mLayoutManager
//        rvLeads?.setHasFixedSize(true)
//    }
//
//    private fun setRVScrollListener() {
//        mLayoutManager = LinearLayoutManager(activity)
//        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as LinearLayoutManager)
//        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
//            override fun onLoadMore() {
//                println("working..........")
//                LoadMoreData()
//            }
//        })
//        rvLeads?.addOnScrollListener(scrollListener)
//    }
//    private fun LoadMoreData() {
//        // adapter?.addLoadingView()
//        //loadMoreItemsCells = ArrayList()
//        val start = ++position;
//        presenter.leadmore(userId!!, "4", date,date2, "added",start.toString(), "10", edtSearch.text.toString())
//    }
//
    override fun loginError(msg: String) {
        try {
            rvLeads.adapter = null
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    override fun onFollowupAdd(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        presenter.lead(
            userId!!,
            param1!!,
            date,
            date2,
            "added",
            "0",
            "10",
            edtSearch.text.toString()
        )

    }

    override fun onHeadingSuccess(data: List<HeadingList>) {

        val headinglist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                headinglist.add(data.get(i)?.heading!!)
            }
        }
        val classdialog = HeadingDialog(data,headinglist)
        classdialog.setBusId(heading_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }
//
//    override fun LeadMore(lead: MutableList<GetLeadsPojo?>) {
//        try {
//            //Toast.makeText(activity!!, "Successfully posted", Toast.LENGTH_LONG).show()
//            //adapter?.removeLoadingView()
//            adapter?.addData(lead)
//            scrollListener.setLoaded()
//            rvLeads.post {
//                adapter?.notifyDataSetChanged()
//            }
//        } catch (e: java.lang.Exception) {
//            e.printStackTrace()
//        }
//    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String) =
            FollowUpTask().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    //putString(ARG_PARAM2, param2)
                }
            }
    }

}