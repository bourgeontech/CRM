package com.git.crm.EditLeadTwo

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import com.git.crm.Dialogs.EmploymentDialog
import com.git.crm.Dialogs.IncomeDialog
import com.git.crm.Dialogs.PlanDialog
import com.git.crm.EditLeadThree.EditLeadThreeFragment
import com.git.crm.MainActivity
import com.git.crm.Pojo.EmploymentList
import com.git.crm.Pojo.IncomeList
import com.git.crm.Pojo.PlanToBuyList
import com.git.crm.Pojo.ViewLead
import com.git.crm.R
import com.git.crm.Utils.NetworkConnection

import kotlinx.android.synthetic.main.fragment_customer_details_two.*
import kotlinx.android.synthetic.main.fragment_customer_details_two.ivback
import kotlinx.android.synthetic.main.fragment_customer_details_two.progress
import kotlinx.android.synthetic.main.fragment_customer_details_two.tvNext
import java.text.SimpleDateFormat
import java.util.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
private const val ARG_PARAM8 = "param8"
private const val ARG_PARAM10 = "param10"
private const val ARG_PARAM11 = "param11"

class EditLeadTwoFragment : Fragment(), EditLeadTwoView {
    private var income: String = ""
    private lateinit var income_dialogvalues: IncomeDialog.OnBusIdvalue
    private var plantobuyid: String = ""
    private lateinit var data: ViewLead
    private lateinit var plan_dialogvalues: PlanDialog.OnBusIdvalue
    private val presenter = EditLeadTwoPresenter(this, EditLeadTwoInteractor())
    private var employmentid: String = ""
    private lateinit var employment_dialogvalues: EmploymentDialog.OnBusIdvalue
    private var name: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var pincode: String? = null
    private var state: String? = null
    private var district: String? = null
    private var location: String? = null
    private var lastname: String? = null
    private var source: String? = null
    private var subsource: String? = null

    // private lateinit var data: ViewLead
    private var date: String = ""
    var cal = Calendar.getInstance()
    private var sendFormat: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(ARG_PARAM1)
            email = it.getString(ARG_PARAM2)
            phone = it.getString(ARG_PARAM3)
            pincode = it.getString(ARG_PARAM4)
            state = it.getString(ARG_PARAM5)
            district = it.getString(ARG_PARAM6)
            location = it.getString(ARG_PARAM7)
            lastname = it.getString(ARG_PARAM8)
            source = it.getString(ARG_PARAM10)
            subsource = it.getString(ARG_PARAM11)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_customer_details_two, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Toast.makeText(activity,name,Toast.LENGTH_SHORT).show()
        (activity as MainActivity?)!!.setVisibility("")
        edtIncome.setText(data?.data?.get(0)?.anualIncome.toString())
        //edtBudget.setText(data?.data?.get(0)?.budget.toString())
        edtEmployment.setText(data?.data?.get(0)?.employment.toString())

        if (data?.data?.get(0)?.plantoBuy == null) {
            tvPlantobuy.setText("NA")
        } else
            tvPlantobuy.setText(data?.data?.get(0)?.plantoBuy.toString())
        plantobuyid = data?.data?.get(0)?.plantoBuyId.toString()
        employmentid = data?.data?.get(0)?.employmentId.toString()
        employment_dialogvalues = object : EmploymentDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                edtEmployment.text = name
                employmentid = id
            }

        }
        income_dialogvalues = object : IncomeDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                edtIncome.text = name
            }

        }
        plan_dialogvalues = object : PlanDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvPlantobuy.text = name
                plantobuyid = id
            }

        }
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
        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        tvNext.setOnClickListener {
            if (valid()) {
                var confirmfragment =
                    EditLeadThreeFragment.newInstance(
                        name!!,
                        email!!,
                        phone!!,
                        pincode!!,
                        state!!,
                        district!!,
                        location!!,
                        employmentid,
                        edtIncome.text.toString(),
                        "",
                        edtLeadDate.text.toString(),
                        edtLeadId.text.toString(),
                        plantobuyid,
                        lastname,
                        data,
                        source!!,
                        subsource
                    )
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fl_container,
                        confirmfragment,
                        EditLeadThreeFragment.javaClass.name
                    )
                    ?.addToBackStack("")?.commit()
            }
        }
//        val c = Calendar.getInstance().time
//        println("Current time => $c")
//
//        val df = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
//        val formattedDate = df.format(c)
        edtLeadDate.text = data?.data?.get(0)?.leadDate.toString()
//        edtLeadDate.setOnClickListener {
//            val mDatePicker = DatePickerDialog(
//                requireActivity()!!,
//                dateSetListener,
//                // set DatePickerDialog to point to today's date when it loads up
//                cal.get(Calendar.YEAR),
//                cal.get(Calendar.MONTH),
//                cal.get(Calendar.DAY_OF_MONTH)
//            )
//            // mDatePicker.datePicker.minDate=System.currentTimeMillis()-1000;
//            mDatePicker.show()
//        }
        edtEmployment.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getEmployment()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvPlantobuy.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getPlan()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        edtIncome.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getIncome()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd MMM yyyy"
        //  val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        //textDate=sdf.format(cal.getTime())
        edtLeadDate!!.text = sdf.format(cal.getTime())
        sendFormat = "dd MM yyyy" // mention the format you need
        val sdf1 = SimpleDateFormat(sendFormat, Locale.US)
        date = sdf1.format(cal.getTime())

    }

    private fun valid(): Boolean {
        if (edtEmployment.text.length == 0) {
            edtEmployment.setError("Enter Employment")
        } else if (edtIncome.text.length == 0) {
            Toast.makeText(activity, "Select Annual Income", Toast.LENGTH_SHORT).show()
        }
//        else if (edtBudget.text.length == 0) {
//            edtBudget.setError("Enter Budget")
//        }
//        else  if(date.length==0)
//        {
//            Toast.makeText(activity,"Select Lead Date",Toast.LENGTH_SHORT).show()
//        }else  if(edtLeadId.text.length==0)
//        {
//            edtLeadId.setError("Enter Lead LoginId")
//        }
        else return true

        return false

    }

    companion object {


        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            param3: String,
            param4: String,
            param5: String,
            param6: String,
            param7: String,
            param8: String,
            param9: ViewLead,
            param10: String,
            param11: String

        ) =
            EditLeadTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM6, param6)
                    putString(ARG_PARAM7, param7)
                    putString(ARG_PARAM8, param8)
                    data = param9
                    putString(ARG_PARAM10, param10)
                    putString(ARG_PARAM11, param11)
                }
            }
    }

    override fun showProgress() {
        try {
            progress.visibility = View.VISIBLE
        } catch (e: Exception) {

        }
    }

    override fun hideProgress() {
        try {
            progress.visibility = View.GONE
        } catch (e: Exception) {

        }
    }

    override fun Plan(plan: List<PlanToBuyList>) {
        val classdialog = PlanDialog(plan)
        classdialog.setBusId(plan_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun EmploymentSuccess(data: List<EmploymentList>) {
        val classdialog = EmploymentDialog(data)
        classdialog.setBusId(employment_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    override fun IncomeSuccess(data: List<IncomeList>) {
        val classdialog = IncomeDialog(data)
        classdialog.setBusId(income_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }
}