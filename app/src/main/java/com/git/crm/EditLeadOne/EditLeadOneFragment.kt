package com.git.crm.EditLeadOne

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.git.crm.Dialogs.*
import com.git.crm.EditLeadTwo.EditLeadTwoFragment
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.git.crm.R
import com.git.crm.Utils.NetworkConnection
import kotlinx.android.synthetic.main.fragment_customer_details_one.*
import kotlinx.android.synthetic.main.fragment_customer_details_one.progress
import java.util.regex.Pattern

//private const val ARG_PARAM1 = "param1"
private lateinit var data: ViewLead

class EditLeadOneFragment : Fragment(), EditLeadOneView {
    private var subsourceid: String = ""
    private lateinit var sub_source_dialogvalues: SubSourceDialog.OnBusIdvalue
    private lateinit var sourceid: String
    private lateinit var source_dialogvalues: SourceDialog.OnBusIdvalue
    private var locationid: String = ""
    private lateinit var location_dialogvalues: LocationDialog.OnBusIdvalue
    private var districtid: String = ""
    private var stateid: String = ""
    private lateinit var state_dialogvalues: StateDialog.OnBusIdvalue
    private lateinit var district_dialogvalues: DistrictDialog.OnBusIdvalue
    val presenter = EditLeadOnePresenter(this, EditLeadOneInteractor())
//    private var param1: String? = null
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//
//        }
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_customer_details_one, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.setVisibility("")
        edtName.setText(data?.data?.get(0)?.name)
        edtLastName.setText(data?.data?.get(0)?.lastName)
        edtPincode.setText(data?.data?.get(0)?.pincode)
        edtEmail.setText(data?.data?.get(0)?.emailId)
        edtPhone.setText(data?.data?.get(0)?.contact)
        tvState.setText(data?.data?.get(0)?.state)
        tvDistrict.setText(data?.data?.get(0)?.district)
        tvSource.setText(data?.data?.get(0)?.source?.toString())

        sourceid = data?.data?.get(0)?.sourceId?.toString()!!
        subsourceid = data?.data?.get(0)?.subSourceId?.toString()!!
        tvSubsource.setText(data?.data?.get(0)?.subSource?.toString())
        if (data?.data?.get(0)?.location == null) {
            tvLocation.setText("NA")
        } else
            tvLocation.setText(data?.data?.get(0)?.location.toString())
        districtid = data?.data?.get(0)?.districtId.toString()
        stateid = data?.data?.get(0)?.stateId.toString()
        if (data?.data?.get(0)?.locationId != null)
            locationid = data?.data?.get(0)?.locationId.toString()
        else
            locationid = ""
        district_dialogvalues = object : DistrictDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                districtid = id
                tvDistrict.text = name
                locationid = ""
                tvLocation.text = ""
            }

        }
        state_dialogvalues = object : StateDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                stateid = id
                tvState.text = name
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
            }

        }

        source_dialogvalues = object : SourceDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvSource.text = name
                sourceid = id
                tvSubsource.text = ""
                subsourceid = ""
            }

        }
        sub_source_dialogvalues = object : SubSourceDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvSubsource.text = name
                subsourceid = id
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
                    EditLeadTwoFragment.newInstance(
                        edtName.text.toString(),
                        edtEmail.text.toString(),
                        edtPhone.text.toString(),
                        edtPincode.text.toString(),
                        stateid,
                        districtid,
                        locationid,
                        edtLastName.text.toString(), data, sourceid!!, subsourceid!!
                    )
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fl_container,
                        confirmfragment,
                        EditLeadTwoFragment.javaClass.name
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

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    override fun LocationSuccess(data: List<LocationList>) {
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

    companion object {


        @JvmStatic
        fun newInstance(param1: ViewLead) =
            EditLeadOneFragment().apply {
                arguments = Bundle().apply {
                    data = param1
                }
            }
    }

}


