package com.git.crm.SuccessFragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.git.crm.Dialogs.HeadingDialog
import com.git.crm.MainActivity
import com.git.crm.Pojo.HeadingList
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_container.*
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.*
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.edtHeading
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.edtRemark
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.tvDelete
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.tvSave
import kotlinx.android.synthetic.main.follow_up_bottomsheet_two.view.tvTime
import kotlinx.android.synthetic.main.fragment_customer_details_four.*
import kotlinx.android.synthetic.main.fragment_success.*
import kotlinx.android.synthetic.main.row_lead.view.*
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SuccessFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SuccessFragment : Fragment(), SuccessView {
    // TODO: Rename and change types of parameters
    private lateinit var heading_dialogvalues: HeadingDialog.OnBusIdvalue
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var bottomSheet: View
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = SuccessPresenter(this, SuccessInteractor())
    private var date: String = ""
    var cal = Calendar.getInstance()
    private var date2: String = ""
    var cal2 = Calendar.getInstance()
    private var sendFormat: String = ""
    private var sendFormat2: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

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
            }
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_success, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)

        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        (activity as MainActivity?)!!.setVisibility("")
        if (param1.equals("newlead")) {
            tvText.text = "Lead Added Successfully"
        } else {
            tvText.text = "Lead Updated Successfully"
        }
        heading_dialogvalues = object : HeadingDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                bottomSheet.edtHeading.setText(name)
            }

        }
        Handler().postDelayed({
            logout()
//            val fm: FragmentManager = requireActivity()!!.supportFragmentManager
//            for (i in 0 until fm.getBackStackEntryCount()) {
//                fm.popBackStack()
//            }

        }, 2000)
    }

    private fun logout() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity()!!)
        // builder.setTitle("Logout")
        builder.setMessage("Do you want to add follow up on this lead?")
        builder.setPositiveButton("YES") { dialog, which ->
            getBottomSheet()
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
            val fm: FragmentManager = requireActivity()!!.supportFragmentManager
            for (i in 0 until fm.getBackStackEntryCount()) {
                fm.popBackStack()
            }
        }

        builder.show()
    }

    fun heading(heading: String) {

        tvwelcome.text = heading

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SuccessFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
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
        bottomSheet = layoutInflater.inflate(R.layout.follow_up_bottomsheet_two, null)
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView1(bottomSheet.tvDateTwo)
            }
        }
        bottomSheet.tvDateTwo?.text = formattedDate
        bottomSheet.tvTime?.text = formattedTime
        //   bottomSheetDialog.show()
        bottomSheet.edtHeading?.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getHeading()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

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
        bottomSheet.tvSave?.setOnClickListener {
            presenter.savefollow(
                userId!!,
                param2!!,
                bottomSheet.edtHeading?.text.toString(),
                bottomSheet.edtRemark?.text.toString(),
                bottomSheet.tvDateTwo?.text.toString() + " " + bottomSheet.tvTime.text.toString()
            )
            // lead_interface.onOrderselection(salesId.toString(),bottomSheet.edtHeading?.text.toString(),bottomSheet.edtRemark?.text.toString(),bottomSheet.tvDate?.text.toString(),"save")

            dialog.dismiss()
        }
        bottomSheet.tvDelete?.setOnClickListener {
            val fm: FragmentManager = requireActivity()!!.supportFragmentManager
            for (i in 0 until fm.getBackStackEntryCount()) {
                fm.popBackStack()
            }
            // lead_interface.onOrderselection(salesId.toString(),bottomSheet.edtHeading?.text.toString(),bottomSheet.edtRemark?.text.toString(),bottomSheet.tvDate?.text.toString(),"delete")

            dialog.dismiss()
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

        bottomSheet.tvDateTwo?.setOnClickListener {
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

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun loginError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        val fm: FragmentManager = requireActivity()!!.supportFragmentManager
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

    override fun onFollowupAdd(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        val fm: FragmentManager = requireActivity()!!.supportFragmentManager
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

    override fun onHeadingSuccess(data: List<HeadingList>) {
        val headinglist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                headinglist.add(data.get(i)?.heading!!)
            }
        }
        val classdialog = HeadingDialog(data, headinglist)
        classdialog.setBusId(heading_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }
}