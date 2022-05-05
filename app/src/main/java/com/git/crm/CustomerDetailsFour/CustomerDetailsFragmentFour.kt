package com.git.crm.CustomerDetailsFour

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.SuggestedAdapter
import com.git.crm.Dialogs.*
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.git.crm.R
import com.git.crm.SuccessFragment.SuccessFragment
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_customer_details_four.*
import kotlinx.android.synthetic.main.fragment_customer_details_four.ivback
import kotlinx.android.synthetic.main.fragment_customer_details_four.progress
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.regex.Pattern


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM6 = "param6"
private const val ARG_PARAM7 = "param7"
private const val ARG_PARAM8 = "param8"
private const val ARG_PARAM9 = "param9"
private const val ARG_PARAM10 = "param10"
private const val ARG_PARAM11 = "param11"
private const val ARG_PARAM12 = "param12"
private const val ARG_PARAM13 = "param13"
private const val ARG_PARAM14 = "param14"
private const val ARG_PARAM15 = "param15"
private const val ARG_PARAM16 = "param16"
private const val ARG_PARAM17 = "param17"
private const val ARG_PARAM18 = "param18"

class CustomerDetailsFragmentFour : Fragment(), CustomerPageFourView {


    private var addmorestatus: String="0"
    private var model2id: String=""
    private var model2name: String=""
    private lateinit var make_dialogvalues_three: MakeDialogThree.OnBusIdvalue
    private var make2id: String=""
    private var make2name: String=""
    private lateinit var model_dialogvalues_three: ModelDialogThree.OnBusIdvalue
    private var model1id: String=""
    private var make1id: String=""
    private var wrongpattern: String = "0"
    private var usertype: String? = ""
    private var branch: String? = ""
    private var vehiclenumber: String = ""
    private var testdrive: String = ""
    private var userName: String? = ""
    private lateinit var userId: String
    private lateinit var preferenceObj: PreferenceRequestHelper
    private var leadassignedid: String = ""
    private lateinit var user_dialogvalues: UserDialog.OnBusIdvalue
    private var branchid: String = ""
    private lateinit var branch_dialogvalues: BranchDialog.OnBusIdvalue
    private var leadstatusid: String = ""
    private lateinit var lead_dialogvalues: LeadDialog.OnBusIdvalue
    private lateinit var model_dialogvalues_two: ModelDialogTwo.OnBusIdvalue
    private lateinit var make_dialogvalues_two: MakeDialogTwo.OnBusIdvalue
    private var selectposition: Int = 0
    private lateinit var make_bottom_dialogvalues: SuggestedAdapter.OnBusdetails
    private val presenter = CustomerPageFourPresenter(this, CustomerPageFourInteractor())
    private var name: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var pincode: String? = null
    private var state: String? = null
    private var district: String? = null
    private var location: String? = null
    private var employment: String? = null
    private var income: String? = null
    private var budjet: String? = null
    private var leaddate: String? = null
    private var leadloginid: String? = null
    private var arrayone: String? = null
    private var array: String? = null
    private var plantobuyid: String? = null
    private var lastname: String? = null
    private var sourceid: String? = null
    private var subsourceid: String? = null
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
            employment = it.getString(ARG_PARAM8)
            income = it.getString(ARG_PARAM9)
            budjet = it.getString(ARG_PARAM10)
            leaddate = it.getString(ARG_PARAM11)
            leadloginid = it.getString(ARG_PARAM12)
            arrayone = it.getString(ARG_PARAM13)
            array = it.getString(ARG_PARAM14)
            plantobuyid = it.getString(ARG_PARAM15)
            lastname = it.getString(ARG_PARAM16)
            sourceid = it.getString(ARG_PARAM17)
            subsourceid = it.getString(ARG_PARAM18)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_customer_details_four, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.setVisibility("")
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        userName = preferenceObj.getStringValue(Constants.PRES_NAME, "")
        branch = preferenceObj.getStringValue(Constants.PRES_Branch, "")
        branchid = preferenceObj.getStringValue(Constants.PRES_BRANCH_ID, "")
        usertype = preferenceObj.getStringValue(Constants.PRES_USERTYPE, "")

        if (usertype.equals("E")) {
            llAssign.visibility = View.GONE
        } else {
            llAssign.visibility = View.VISIBLE
        }


        tvBranch.setText(branch)
        tvAssigned.setText(userName)
        tvLead.text = "New Lead"
        leadstatusid = "1"
        leadassignedid = userId
        make_bottom_dialogvalues = object : SuggestedAdapter.OnBusdetails {
            override fun onBus(position: Int, type: String) {
//                selectposition = position
//                if (type.equals("make")) {
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getMake2()
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                } else {
//
//                    val row2: View? =
//                        rvSuggested?.getLayoutManager()?.findViewByPosition(position)
//                    val make = row2?.findViewById<View>(R.id.tvMake) as TextView
//                    val makeid = row2?.findViewById<View>(R.id.tvMakeId) as TextView
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                    presenter.getModel2(makeid.text.toString())
//                } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                }

            }

        }
        lead_dialogvalues = object : LeadDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvLead.text = name
                leadstatusid = id
            }

        }
        model_dialogvalues_three=object :ModelDialogThree.OnBusIdvalue{
            override fun onBusId(id: String, name: String) {
                tvModelTwo.text=name
                model2name=name
                model2id=id
            }

        }
        make_dialogvalues_three=object :MakeDialogThree.OnBusIdvalue{
            override fun onBusId(id: String, name: String) {
                tvMakeTwo.text=name
                make2name=name
                make2id=id
            }

        }

        user_dialogvalues = object : UserDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvAssigned.text = name
                leadassignedid = id
            }

        }
        branch_dialogvalues = object : BranchDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvBranch.text = name
                branchid = id
            }

        }
        make_dialogvalues_two = object : MakeDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMakeOne.text=name
                make1id=id
//                try {
//                    val row2: View? =
//                        rvSuggested?.getLayoutManager()?.findViewByPosition(selectposition)
//                    val make = row2?.findViewById<View>(R.id.tvMake) as TextView
//                    make.text = name
//                    val makeid = row2?.findViewById<View>(R.id.tvMakeId) as TextView
//                    makeid.text = id
//
//                    val model = row2?.findViewById<View>(R.id.tvModel) as TextView
//                    model.text = ""
//                    val modelid = row2?.findViewById<View>(R.id.tvModelId) as TextView
//                    modelid.text = ""
//
//                    val edtVehicleNumber =
//                        row2?.findViewById<View>(R.id.edtVehicleNumber) as EditText
//                    edtVehicleNumber.setText("")
//                } catch (e: Exception) {
//
//                }
            }

        }
        model_dialogvalues_two = object : ModelDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModelOne.text=name
                model1id=id
//                try {
//                    val row2: View? =
//                        rvSuggested?.getLayoutManager()?.findViewByPosition(selectposition)
//                    val model = row2?.findViewById<View>(R.id.tvModel) as TextView
//                    model.text = name
//                    val modelid = row2?.findViewById<View>(R.id.tvModelId) as TextView
//                    modelid.text = id
//
//                    val edtVehicleNumber =
//                        row2?.findViewById<View>(R.id.edtVehicleNumber) as EditText
//                    edtVehicleNumber.setText("")
//                } catch (e: Exception) {
//
//                }
            }

        }

        rgOne.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbyes) {
                llvehicle.visibility = View.VISIBLE
                testdrive = "yes"
            } else if (checkedId == R.id.rbno) {
                llvehicle.visibility = View.GONE
                testdrive = "no"
            }
        })

//        var data: MutableList<String> = ArrayList<String>()
//        data.add("")
//        rvSuggested?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        val adapter =
//            SuggestedAdapter(data, activity, rvSuggested, fragmentManager, make_bottom_dialogvalues)
//        rvSuggested?.adapter = adapter
//
        tvMakeOne.setOnClickListener {
            presenter.getMake2("1")
        }
        tvModelOne.setOnClickListener {
            presenter.getModel2(make1id,"1")
        }
        tvMakeTwo.setOnClickListener {
            presenter.getMake2("2")
        }
        tvModelTwo.setOnClickListener {
            presenter.getModel2(make2id,"2")
        }

        tvLead.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getLead()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvBranch.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getBranch()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvAssigned.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getAssigned()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        tvAddMore.setOnClickListener {
            addmorestatus="1"
            llTwo.visibility=View.VISIBLE
        }

        edtRemark.addTextChangedListener(object : TextWatcher {
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
                tvCount.text = (edtRemark.text.length).toString() + "/100"
            }
        })
//        if (branchid.equals("0")) {
//            branchid = ""
//        }

        tvSubmit.setOnClickListener {
            if(validNumber()){
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                val array3 = JSONArray()
                if (testdrive.equals("yes")) {

                    val obj3 = JSONObject()
                    val obj4 = JSONObject()
                    try {
                        //   if (validNumber(edtVehicleNumber.text.toString())) {
                        obj3.put("MakeId", make1id)
                        obj3.put("ModelId", model1id)
                        obj3.put("TestDriveNo", edtVehicleNumberOne.text.toString())
                        array3.put(obj3)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    if (addmorestatus.equals("1")) {
                        try {
                            //   if (validNumber(edtVehicleNumber.text.toString())) {
                            obj4.put("MakeId", make2id)
                            obj4.put("ModelId", model2id)
                            obj4.put("TestDriveNo", edtVehicleNumberTwo.text.toString())
                            array3.put(obj4)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }


                    vehiclenumber = edtVehicleNumber.text.toString()
                } else {
                    val obj3 = JSONObject()
                    try {
                        obj3.put("MakeId", "")
                        obj3.put("ModelId", "")
                        obj3.put("TestDriveNo", "")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    array3.put(obj3)
                    vehiclenumber = ""
                }
                val jsonArray = JSONArray(array)
                val jsonArray2 = JSONArray(arrayone)
                    presenter.getSend(
                        userId!!,
                        name!!,
                        email!!,
                        phone!!,
                        pincode!!,
                        state!!,
                        district!!,
                        location!!,
                        employment!!,
                        income!!,
                        budjet!!,
                        leaddate!!,
                        leadloginid!!,
                        jsonArray2!!,
                        jsonArray!!,
                        array3,
                        branchid!!,
                        leadstatusid!!,
                        leadassignedid!!,
                        edtRemark.text.toString(),
                        plantobuyid,
                        lastname,
                        sourceid!!,
                        subsourceid!!,
                        vehiclenumber
                    )

            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()

        }
        }
    }

    private fun validNumber(): Boolean {
        if (edtVehicleNumberOne.text.length>0&&(!(isValidVehicle(edtVehicleNumberOne.text.toString())))) {
            Toast.makeText(activity, "Format : MH 00 j 0000", Toast.LENGTH_SHORT).show()
        } else  if (edtVehicleNumberTwo.text.length>0&&(!(isValidVehicle(edtVehicleNumberTwo.text.toString())))) {
            Toast.makeText(activity, "Format : MH 00 j 0000", Toast.LENGTH_SHORT).show()
        }
          else
            return true

        return false
    }


    private fun getBranch() {
        presenter.getModel()
    }

    private fun getAssigned() {
        presenter.getVariant(userId)
    }

    private fun getLead() {
        presenter.getMake()
    }

//

    private fun isValidVehicle(email: String): Boolean {
        return Pattern.compile(
            "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}\$"
        ).matcher(email).matches()
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

    override fun Make(data: List<LeadList>) {
        val classdialog = LeadDialog(data)
        classdialog.setBusId(lead_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun Make2(data: List<MakeList>,type:String) {
        val makelist = ArrayList<String>()
        val makeidlist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                makelist.add(data.get(i)?.make!!)
                makeidlist.add(data.get(i)?.makeId.toString())
            }
        }
        if(type.equals("1")){
        val classdialog = MakeDialogTwo(data, makelist, makeidlist)
        classdialog.setBusId(make_dialogvalues_two)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
        }
        else{
            val classdialog = MakeDialogThree(data, makelist, makeidlist)
            classdialog.setBusId(make_dialogvalues_three)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }
    }

    override fun VariantSuccess(data: List<UserList>) {
        val classdialog = UserDialog(data)
        classdialog.setBusId(user_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    override fun ModelSuccess(data: List<BranchList>) {
        val classdialog = BranchDialog(data)
        classdialog.setBusId(branch_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun sendSucces(message: String, leadid: String) {
        // Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        // fragmentManager?.beginTransaction()?.replace(R.id.fl_container, SuccessFragment())?.addToBackStack("")?.commitAllowingStateLoss()
        var confirmfragment =
            SuccessFragment.newInstance(
                "newlead", leadid
            )
        fragmentManager?.beginTransaction()
            ?.replace(
                R.id.fl_container,
                confirmfragment,
                SuccessFragment.javaClass.name
            )
            ?.addToBackStack("")?.commit()

    }


    override fun ModelSuccessTwo(data: List<ModelList>,type:String) {
        if(type.equals("1")){
        val classdialog = ModelDialogTwo(data)
        classdialog.setBusId(model_dialogvalues_two)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
        }else{
            val classdialog = ModelDialogThree(data)
            classdialog.setBusId(model_dialogvalues_three)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(
            param1: String, param2: String, param3: String,
            param4: String,
            param5: String,
            param6: String,
            param7: String,
            param8: String,
            param9: String,
            param10: String,
            param11: String,
            param12: String,
            param13: JSONArray,
            param14: JSONArray,
            param15: String?,
            param16: String?,
            param17: String?,
            param18: String?
        ) =
            CustomerDetailsFragmentFour().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM6, param6)
                    putString(ARG_PARAM7, param7)
                    putString(ARG_PARAM8, param8)
                    putString(ARG_PARAM9, param9)
                    putString(ARG_PARAM10, param10)
                    putString(ARG_PARAM11, param11)
                    putString(ARG_PARAM12, param12)
                    putString(ARG_PARAM13, param13.toString())
                    putString(ARG_PARAM14, param14.toString())
                    putString(ARG_PARAM15, param15)
                    putString(ARG_PARAM16, param16)
                    putString(ARG_PARAM17, param17)
                    putString(ARG_PARAM18, param18)

                }
            }
    }


}