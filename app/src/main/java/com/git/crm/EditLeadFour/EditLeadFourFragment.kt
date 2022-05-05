package com.git.crm.EditLeadFour

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.git.crm.Adapter.SuggestedAdapterTwo
import com.git.crm.Dashboard.DashboardFragment
import com.git.crm.Dialogs.*
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.git.crm.R
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
private const val ARG_PARAM18 = "param18"
private const val ARG_PARAM19 = "param19"

class EditLeadFourFragment : Fragment(), EditLeadFourView {
    private var make2name: String=""
    private var model2name: String=""
    private lateinit var make_dialogvalues_three: MakeDialogThree.OnBusIdvalue
    private lateinit var model_dialogvalues_three: ModelDialogThree.OnBusIdvalue
    private var modelidTwo: String=""
    private var makeidTwo: String=""
    private var addmorestatus: String="0"
    private var modelidOne: String=""
    private var makeidOne: String=""
    private var usertype: String? = ""
    private var vehiclenumber: String = ""
    private var testdrive: String = ""
    private var userName: String = ""
    private var subsourceid: String = ""
    private lateinit var sub_source_dialogvalues: SubSourceDialog.OnBusIdvalue
    private var sourceid: String = ""
    private lateinit var source_dialogvalues: SourceDialog.OnBusIdvalue
    private lateinit var mydata: ViewLead
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
    private lateinit var make_bottom_dialogvalues: SuggestedAdapterTwo.OnBusdetails
    private val presenter = EditLeadFourPresenter(this, EditLeadFourInteractor())
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

    private var data = ArrayList<String>()
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
            sourceid = it.getString(ARG_PARAM18)!!
            subsourceid = it.getString(ARG_PARAM19)!!
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
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        userName = preferenceObj.getStringValue(Constants.PRES_NAME, "")
        usertype = preferenceObj.getStringValue(Constants.PRES_USERTYPE, "")

        if (usertype.equals("E")) {
            llAssign.visibility = View.GONE
        } else {
            llAssign.visibility = View.VISIBLE
        }
        leadstatusid = mydata?.data?.get(0)?.leadStatusId?.toString()!!
        tvLead.setText(mydata?.data?.get(0)?.leadStatus?.toString())
        (activity as MainActivity?)!!.setVisibility("")
        tvAssigned.setText(mydata?.data?.get(0)?.assignedto?.toString())
        leadassignedid = mydata?.data?.get(0)?.assignedtoId?.toString()!!

        tvBranch.setText(mydata?.data?.get(0)?.branch?.toString())
        branchid = mydata?.data?.get(0)?.branchId?.toString()!!
        edtRemark.setText(mydata?.data?.get(0)?.remark?.toString()!!)
        //edtBudget.setText(mydata?.data?.get(0)?.budget?.toString()!!)

        model_dialogvalues_three=object :ModelDialogThree.OnBusIdvalue{
            override fun onBusId(id: String, name: String) {
                tvModelTwo.text=name
                model2name=name
                modelidTwo=id
            }

        }
        make_dialogvalues_three=object :MakeDialogThree.OnBusIdvalue{
            override fun onBusId(id: String, name: String) {
                tvMakeTwo.text=name
                make2name=name
                makeidTwo=id
            }

        }
        make_bottom_dialogvalues = object : SuggestedAdapterTwo.OnBusdetails {
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
//                    val make = row2?.findViewById<View>(R.id.tvMake) as? TextView
//                    val makeid = row2?.findViewById<View>(R.id.tvMakeId) as? TextView
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getModel2(makeid?.text.toString())
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                }

            }

        }
//        source_dialogvalues = object : SourceDialog.OnBusIdvalue {
//            override fun onBusId(id: String, name: String) {
//                tvSource.text = name
//                sourceid = id
//            }
//
//        }
//        sub_source_dialogvalues = object : SubSourceDialog.OnBusIdvalue {
//            override fun onBusId(id: String, name: String) {
//                tvSubsource.text = name
//                subsourceid = id
//            }
//
//        }
        rgOne.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbyes) {
                llvehicle.visibility = View.VISIBLE
                testdrive = "yes"
            } else if (checkedId == R.id.rbno) {
                llvehicle.visibility = View.GONE
                testdrive = "no"
            }
        })
        if (mydata?.suggestedVehicle?.get(0)?.TestDriveNo?.length!! > 0) {
            llvehicle.visibility = View.VISIBLE
            testdrive = "yes"
            rbyes.isChecked = true
        } else {
            llvehicle.visibility = View.GONE
            testdrive = "no"
            rbno.isChecked = true
        }

        lead_dialogvalues = object : LeadDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvLead.text = name
                leadstatusid = id
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
//                val row2: View? =
//                    rvSuggested?.getLayoutManager()?.findViewByPosition(selectposition)
//                val make = row2?.findViewById<View>(R.id.tvMake) as? TextView
//                make?.text = name
//                val makeid = row2?.findViewById<View>(R.id.tvMakeId) as? TextView
//                makeid?.text = id
//
//                val model = row2?.findViewById<View>(R.id.tvModel) as? TextView
//                model?.text = ""
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as? TextView
//                modelid?.text = ""
//
//                val edtVehicleNumber =
//                    row2?.findViewById<View>(R.id.edtVehicleNumber) as? EditText
//
                edtVehicleNumber?.setText("")
            }

        }
        model_dialogvalues_two = object : ModelDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
//                val row2: View? =
//                    rvSuggested?.getLayoutManager()?.findViewByPosition(selectposition)
//                val model = row2?.findViewById<View>(R.id.tvModel) as? TextView
//                model?.text = name
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as? TextView
//                modelid?.text = id
//
//                val edtVehicleNumber =
//                    row2?.findViewById<View>(R.id.edtVehicleNumber) as? EditText

                edtVehicleNumber?.setText("")
            }

        }

//        var data: MutableList<String> = ArrayList<String>()
//        data.add("")
//        rvSuggested?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        val adapter =
//            SuggestedAdapter(data, activity, rvSuggested, fragmentManager, make_bottom_dialogvalues)
//        rvSuggested?.adapter = adapter


        if (mydata?.suggestedVehicle?.size!! > 0) {
            tvMakeOne.text= mydata?.suggestedVehicle?.get(0)!!.make.toString()
            makeidOne=mydata?.suggestedVehicle?.get(0)!!.makeId.toString()
            tvModelOne.text= mydata?.suggestedVehicle?.get(0)!!.model.toString()
            modelidOne=mydata?.suggestedVehicle?.get(0)!!.modelId.toString()
            edtVehicleNumberOne.setText(mydata?.suggestedVehicle?.get(0)!!.TestDriveNo.toString())

            if(mydata?.suggestedVehicle?.size!! > 1)
            {
                addmorestatus="1"
                tvMakeTwo.text= mydata?.suggestedVehicle?.get(1)!!.make.toString()
                makeidTwo=mydata?.suggestedVehicle?.get(1)!!.makeId.toString()
                tvModelTwo.text= mydata?.suggestedVehicle?.get(1)!!.model.toString()
                modelidTwo=mydata?.suggestedVehicle?.get(1)!!.modelId.toString()
                edtVehicleNumberTwo.setText(mydata?.suggestedVehicle?.get(1)!!.TestDriveNo.toString())
                llTwo.visibility=View.VISIBLE
            }
//            for (i in 0 until mydata?.suggestedVehicle?.size!!) {
//
        //               data.add("")
//            }
//            rvSuggested?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//            val adapter = SuggestedAdapterTwo(
//                data,
//                activity,
//                rvSuggested,
//                fragmentManager,
//                make_bottom_dialogvalues,
//                mydata?.suggestedVehicle!!
//            )
//            rvSuggested?.adapter = adapter

        } else {
            llvehicle.visibility = View.GONE
            rbyes.isChecked = false
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

        tvMakeOne.setOnClickListener {
            presenter.getMake2("1")
        }
        tvModelOne.setOnClickListener {
            presenter.getModel2(makeidOne,"1")
        }
        tvMakeTwo.setOnClickListener {
            presenter.getMake2("2")
        }
        tvModelTwo.setOnClickListener {
            presenter.getModel2(makeidTwo,"2")
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

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
//        tvSource.setOnClickListener {
//            presenter.getSorce()
//        }
//
//        tvSubsource.setOnClickListener {
//            presenter.getSubSorce(sourceid)
//        }

        tvSubmit.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                if(validNumber()){
                val array3 = JSONArray()
                if (testdrive.equals("yes")) {
                    val obj3 = JSONObject()
                    val obj4 = JSONObject()
                    try {
                        //   if (validNumber(edtVehicleNumber.text.toString())) {
                        obj3.put("MakeId", makeidOne)
                        obj3.put("ModelId", modelidOne)
                        obj3.put("TestDriveNo", edtVehicleNumberOne.text.toString())
                        array3.put(obj3)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    if (addmorestatus.equals("1")) {
                        try {
                            //   if (validNumber(edtVehicleNumber.text.toString())) {
                            obj4.put("MakeId", makeidTwo)
                            obj4.put("ModelId", modelidTwo)
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
                    vehiclenumber = edtVehicleNumber.text.toString()
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
                    mydata?.data?.get(0)?.id!!.toString(),
                    sourceid,
                    subsourceid,
                    vehiclenumber,
                    requireActivity()
                )
            }
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

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
    private fun isValidVehicle(email: String): Boolean {
        return Pattern.compile(
            "^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}\$"
        ).matcher(email).matches()
    }

    override fun Make(data: List<LeadList>) {
        Toast.makeText(activity, "1", Toast.LENGTH_SHORT).show()
        val classdialog = LeadDialog(data)
        classdialog.setBusId(lead_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun Make2(data: List<MakeList>, type: String) {

        val makelist = ArrayList<String>()
        val makeidlist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                makelist.add(data.get(i)?.make!!)
                makeidlist.add(data.get(i)?.makeId.toString())
            }
        }
        if(type.equals("1")) {
            val classdialog = MakeDialogTwo(data, makelist, makeidlist)
            classdialog.setBusId(make_dialogvalues_two)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }else
        {
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

    override fun sendSucces(message: String) {

//        var confirmfragment =
//            SuccessFragment.newInstance(
//                "update",
//                leadid
//            )
//        fragmentManager?.beginTransaction()
//            ?.replace(
//                R.id.fl_container,
//                confirmfragment,
//                SuccessFragment.javaClass.name
//            )
//            ?.addToBackStack("")?.commit()
        fragmentManager?.beginTransaction()?.replace(R.id.fl_container, DashboardFragment())
            ?.commitAllowingStateLoss()

    }

    override fun ModelSuccessTwo(data: List<ModelList>, type: String) {
        if(type.equals("1")) {
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
            param17: ViewLead,
            param18: String?,
            param19: String?
        ) =
            EditLeadFourFragment().apply {
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
                    putString(ARG_PARAM18, param18)
                    putString(ARG_PARAM19, param19)
                    mydata = param17

                }
            }
    }


}