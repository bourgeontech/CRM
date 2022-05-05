package com.git.crm.CustomerDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.git.crm.CustomerDetailsTwo.CustomerDetailsFragmentTwo
import com.git.crm.Dialogs.*
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.git.crm.R
import com.git.crm.Utils.NetworkConnection
import com.git.crm.ViewLead.ViewLeadFragment
import kotlinx.android.synthetic.main.fragment_customer_details_one.*
import java.util.regex.Pattern


class CustomerDetailsOneFragment : Fragment(), CustomerPageView {
    private var locationname: String = ""
    private var districtname: String = ""
    private var statename: String = ""
    private var subsourcename: String = ""
    private var sourcename: String = ""
    private var emailstatus: String = ""
    private var phonestatus: String = ""
    private var subsourceid: String = ""
    private lateinit var sub_source_dialogvalues: SubSourceDialog.OnBusIdvalue
    private var sourceid: String = ""
    private lateinit var source_dialogvalues: SourceDialog.OnBusIdvalue
    private var locationid: String = ""
    private lateinit var location_dialogvalues: LocationDialog.OnBusIdvalue
    private var districtid: String = ""
    private var stateid: String = ""
    private lateinit var state_dialogvalues: StateDialog.OnBusIdvalue
    private lateinit var district_dialogvalues: DistrictDialog.OnBusIdvalue
    val presenter = CustomerPageOnePresenter(this, CustomerPageOneInteractor())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_customer_details_one, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        tvSource.text = sourcename
        tvSubsource.text = subsourcename
        tvState.text = statename
        tvDistrict.text = districtname
        tvLocation.text = locationname

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.heading("Customer Details")
        (activity as MainActivity?)!!.setVisibility("")
        source_dialogvalues = object : SourceDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvSource.text = name
                sourcename = name
                sourceid = id
                tvSubsource.text = ""
                subsourceid = ""
            }

        }
        sub_source_dialogvalues = object : SubSourceDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvSubsource.text = name
                subsourceid = id
                subsourcename = name
            }

        }
        district_dialogvalues = object : DistrictDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                districtid = id
                tvDistrict.text = name
                districtname = name
                locationid = ""
                tvLocation.text = ""
            }

        }
        state_dialogvalues = object : StateDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                stateid = id
                tvState.text = name
                statename = name
                districtid = ""
                tvDistrict.text = ""
                locationid = ""
                tvLocation.text = ""
            }

        }

        location_dialogvalues = object : LocationDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                locationid = id
                tvLocation.text = name
                locationname = name
            }

        }
//
//        edtPhone.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable) {
//
//                // you can call or do what you want with your EditText here
//                if (edtPhone.text.length >= 10)
//                    presenter.getValid(edtPhone.text.toString(), "Mobile")
//                // yourEditText...
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//        })

        edtPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Toast.makeText(activity, "Has focus", Toast.LENGTH_SHORT).show()
            } else {
                presenter.getValid(edtPhone.text.toString(), "Mobile")
            }
        }

//        edtEmail.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable) {
//
//                // you can call or do what you want with your EditText here
//                presenter.getValid(edtEmail.text.toString(), "Email")
//                // yourEditText...
//            }
//
//            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//        })

        edtEmail.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                // Toast.makeText(activity, "Has focus", Toast.LENGTH_SHORT).show()
            } else {
                presenter.getValid(edtEmail.text.toString(), "Email")
            }
        }
        tvDistrict.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getDistrict()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvState.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getstate()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvLocation.setOnClickListener {

            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getlocation()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        tvSource.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getSorce()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

        tvSubsource.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.getSubSorce(sourceid)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }


        tvNext.setOnClickListener {
            if (valid()) {

                var confirmfragment =
                    CustomerDetailsFragmentTwo.newInstance(
                        edtName.text.toString(),
                        edtEmail.text.toString(),
                        edtPhone.text.toString(),
                        edtPincode.text.toString(),
                        stateid,
                        districtid,
                        locationid,
                        edtLastName.text.toString(), sourceid!!, subsourceid!!
                    )
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fl_container,
                        confirmfragment,
                        CustomerDetailsFragmentTwo.javaClass.name
                    )
                    ?.addToBackStack("")?.commit()


            }

        }
    }

    private fun valid(): Boolean {
        if (edtPhone.text.length>0&&edtPhone.text.length <10) {
            edtPhone.setError("Enter 10 Digit Number")
        } else if (edtEmail.text.length>0&&!(isValidEmailId(edtEmail.text.toString()))) {
            edtEmail.setError("InValid Email Address")
        } else if (edtPincode.text.length != 6) {
            edtPincode.setError("Enter 6 Digit Pincode")
        } else if (stateid.length == 0) {
            Toast.makeText(activity, "Select State", Toast.LENGTH_SHORT).show()
        } else if (districtid.length == 0) {
            Toast.makeText(activity, "Select District", Toast.LENGTH_SHORT).show()
        }
        else
            return true

        return false
    }

    private fun getlocation() {
        presenter.getLocation(stateid, districtid);
    }

    private fun getstate() {
        presenter.getState();
    }

    private fun getDistrict() {
        presenter.getDistrict(stateid);
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

    override fun District(district: List<DistrictList>) {

        val districtlist = ArrayList<String>()
        val districtidlist = ArrayList<String>()
        if (district.size > 0) {
            for (i in 0 until district.size) {
                districtlist.add(district.get(i)?.district!!)
                districtidlist.add(district.get(i)?.districtId.toString())
            }
        }
        val classdialog = DistrictDialog(district, districtlist, districtidlist)
        classdialog.setBusId(district_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun StateSuccess(data: MutableList<StateList?>) {
        val statelist = ArrayList<String>()
        val stateidlist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                statelist.add(data.get(i)?.state!!)
                stateidlist.add(data.get(i)?.stateId.toString())
            }
        }
        val classdialog = StateDialog(data, statelist, stateidlist)
        classdialog.setBusId(state_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun sourceSucces(data: List<SourceList>) {
        val classdialog = SourceDialog(data)
        classdialog.setBusId(source_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun subsourceSucces(data: List<SubSourceList>) {
        val classdialog = SubSourceDialog(data)
        classdialog.setBusId(sub_source_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun EmailSuccess(msg: String, data: List<LeadDetails>) {
        if (data.size > 0) {
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
            builder.setMessage("There is a lead added using this email do you wish to see?")
            builder.setPositiveButton("YES") { dialog, which ->

                var confirmfragment =
                    ViewLeadFragment.newInstance(
                        data?.get(0)?.salesId!!.toString()
                    )
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fl_container,
                        confirmfragment,
                        ViewLeadFragment.javaClass.name
                    )
                    ?.addToBackStack("")?.commit()
            }

            builder.setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()
        }
//        emailstatus = "not"
//        edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_close_24, 0);
    }

    override fun EmailFaild(msg: String) {
//        emailstatus = "ok"
//        edtEmail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_done_24, 0);
    }

    override fun loginError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun validSucces(msg: String, data: List<LeadDetails>) {
        try {
            if (data.size > 0) {
                val builder = androidx.appcompat.app.AlertDialog.Builder(requireActivity())
                builder.setMessage("There is a lead added using this number do you wish to see?")
                builder.setPositiveButton("YES") { dialog, which ->
                    var confirmfragment =
                        ViewLeadFragment.newInstance(
                            data?.get(0)?.salesId!!.toString()
                        )
                    fragmentManager?.beginTransaction()
                        ?.replace(
                            R.id.fl_container,
                            confirmfragment,
                            ViewLeadFragment.javaClass.name
                        )
                        ?.addToBackStack("")?.commit()

                }

                builder.setNegativeButton("No") { dialog, which ->
                    dialog.dismiss()
                }

                builder.show()
            }
        }catch (e:Exception){

        }
        //Toast.makeText(activity,msg,Toast.LENGTH_SHORT)
//        phonestatus = "not"
//        edtPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_close_24, 0);
    }

    override fun validFaild(msg: String) {
//        phonestatus = "ok"
//        edtPhone.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_done_24, 0);
        //  Toast.makeText(activity,msg,Toast.LENGTH_SHORT)
    }

    override fun LocationSuccess(data: List<LocationList>) {
        try {
            val locationlist = ArrayList<String>()
            val locationidlist = ArrayList<String>()
            if (data.size > 0) {
                for (i in 0 until data.size) {
                    locationlist.add(data.get(i)?.location!!)
                    locationidlist.add(data.get(i)?.locationId.toString())
                }
            }
            val classdialog = LocationDialog(data, locationlist, locationidlist)
            classdialog.setBusId(location_dialogvalues)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }catch (e:Exception){

        }
    }

    private fun isValidEmailId(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }


}


