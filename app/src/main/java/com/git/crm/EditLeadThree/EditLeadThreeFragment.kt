package com.git.crm.EditLeadThree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.PrefferedAdapterTwo
import com.git.crm.Dialogs.*
import com.git.crm.EditLeadFour.EditLeadFourFragment
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.git.crm.R
import com.git.crm.Utils.NetworkConnection

import kotlinx.android.synthetic.main.fragment_customer_details_three.*
import kotlinx.android.synthetic.main.fragment_customer_details_three.progress
import kotlinx.android.synthetic.main.fragment_customer_details_three.tvNext
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
private const val ARG_PARAM16 = "param16"
private const val ARG_PARAM17 = "param17"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerDetailsFragmentThree.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditLeadThreeFragment : Fragment(), EditLeadThreeView {
    private var makeeidTwo: String=""
    private lateinit var make_dialogvalues_three: MakeDialogThree.OnBusIdvalue
    private lateinit var model_dialogvalues_three: ModelDialogThree.OnBusIdvalue
    private var addmorestatus: String=""
    private var model1id: String=""
    private var make1id: String=""
    private var year: String = ""
    private lateinit var year_dialogvalues: YearDialog.OnBusIdvalue
    private lateinit var model_dialogvalues_two: ModelDialogTwo.OnBusIdvalue
    private lateinit var make_dialogvalues_two: MakeDialogTwo.OnBusIdvalue
    private var selectposition: Int = 0
    private var makeid: String = ""
    private lateinit var make_dialogvalues: MakeDialog.OnBusIdvalue
    private var modelid: String = ""
    private lateinit var model_dialogvalues: ModelDialog.OnBusIdvalue
    private var variantid: String = ""
    private lateinit var variant_dialogvalues: VariantDialog.OnBusIdvalue
    private lateinit var mydata: ViewLead
    private lateinit var make_bottom_dialogvalues: PrefferedAdapterTwo.OnBusdetails
    private lateinit var budjet_dialogvalues: BudjetDialog.OnBusIdvalue

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
    private var plantobuyid: String? = null
    private var lastname: String? = null
    private var source: String? = null
    private var subsource: String? = null
    // data: MutableList<String> = ArrayList<String>()
    private var make1name: String=""
    private var model1name: String=""
    private var make2name: String=""
    private var model2name: String=""
    private var modelidTwo: String = ""
    private var data = ArrayList<String>()
    private val presenter = EditLeadThreePresenter(this, EditLeadThreeInteractor())
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
            plantobuyid = it.getString(ARG_PARAM13)
            lastname = it.getString(ARG_PARAM14)
            source = it.getString(ARG_PARAM16)
            subsource = it.getString(ARG_PARAM17)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View =
            inflater.inflate(R.layout.fragment_customer_details_three, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.setVisibility("")
        if (mydata?.currentVehicle?.get(0)?.make == null) {
            tvMake.setText("NA")
        } else
            tvMake.setText(mydata?.currentVehicle?.get(0)?.make.toString())

        if (mydata?.currentVehicle?.get(0)?.model == null) {
            tvModel.setText("NA")
        } else
            tvModel.setText(mydata?.currentVehicle?.get(0)?.model.toString())


        if (mydata?.currentVehicle?.get(0)?.variant == null) {
            tvVariant.setText("NA")
        } else
            tvVariant.setText(mydata?.currentVehicle?.get(0)?.variant.toString())
        tvYear.setText(mydata?.currentVehicle?.get(0)?.year.toString())
        variantid = mydata?.currentVehicle?.get(0)?.variantId.toString()
        modelid = mydata?.currentVehicle?.get(0)?.modelId.toString()
        makeid = mydata?.currentVehicle?.get(0)?.makeId.toString()
        year = mydata?.currentVehicle?.get(0)?.year.toString()

        edtBudget.setText(mydata?.data?.get(0)?.budget.toString())

        make_dialogvalues_three = object : MakeDialogThree.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMake2.text = name
                makeeidTwo = id
                make2name=name
                tvModel2.text = ""
                modelidTwo = ""
            }

        }
        model_dialogvalues_three = object : ModelDialogThree.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModel2.text = name
                model2name=name
                modelidTwo = id
            }

        }

        variant_dialogvalues = object : VariantDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvVariant.text = name
                variantid = id
            }

        }
        budjet_dialogvalues = object : BudjetDialog.OnBusIdvalue {
            override fun onBusId(name: String) {
                edtBudget.text = name
            }

        }
        model_dialogvalues = object : ModelDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModel.text = name
                modelid = id
            }

        }
        make_dialogvalues = object : MakeDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMake.text = name
                makeid = id
            }

        }

        year_dialogvalues = object : YearDialog.OnBusIdvalue {
            override fun onBusId(name: String) {
                tvYear.text = name
                year = name
            }

        }

        make_dialogvalues_two = object : MakeDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMake1.text=name
                make1id=id

//                val row2: View? =
//                    rvPreffered?.getLayoutManager()?.findViewByPosition(selectposition)
//                val make = row2?.findViewById<View>(R.id.tvMake) as? TextView
//                val makeid = row2?.findViewById<View>(R.id.tvMakeId) as? TextView
//                make?.text = name
//                makeid?.text = id
//
//                val model = row2?.findViewById<View>(R.id.tvModel) as? TextView
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as? TextView
//                modelid?.text = ""
//                model?.text = ""
            }

        }
        model_dialogvalues_two = object : ModelDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModel1.text=name
                model1id=id
//                val row2: View? =
//                    rvPreffered?.getLayoutManager()?.findViewByPosition(selectposition)
//                val model = row2?.findViewById<View>(R.id.tvModel) as? TextView
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as? TextView
//                modelid?.text = id
//                model?.text = name
            }

        }


        make_bottom_dialogvalues = object : PrefferedAdapterTwo.OnBusdetails {
            override fun onBus(position: Int, type: String) {

//                selectposition = position
//                if (type.equals("make")) {
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getMake2()
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                } else {
//                    val row2: View? = rvPreffered?.getLayoutManager()?.findViewByPosition(position)
//                    val make = row2?.findViewById<View>(R.id.tvMake) as? TextView
//                    val makeid = row2?.findViewById<View>(R.id.tvMakeId) as? TextView
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getModel2(makeid?.text.toString())
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                }

            }

        }
        if (mydata?.prefferedVehicle?.size!! > 0) {

            tvMake1.text=mydata?.prefferedVehicle?.get(0)?.make
            make1id=mydata?.prefferedVehicle?.get(0)?.makeId.toString()
            tvModel1.text=mydata?.prefferedVehicle?.get(0)?.model
            model1id=mydata?.prefferedVehicle?.get(0)?.modelId.toString()
            if(mydata?.prefferedVehicle?.size!! > 1)
            {
                addmorestatus="1"
                llTwo.visibility=View.VISIBLE
                tvMake2.text=mydata?.prefferedVehicle?.get(1)?.make
                makeeidTwo=mydata?.prefferedVehicle?.get(1)?.makeId.toString()
                tvModel2.text=mydata?.prefferedVehicle?.get(1)?.model
                modelidTwo=mydata?.prefferedVehicle?.get(1)?.modelId.toString()
            }


//            for (i in 0 until mydata?.prefferedVehicle?.size!!) {
//                data.add("")
//            }
//            rvPreffered?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//            val adapter = PrefferedAdapterTwo(
//                data,
//                activity,
//                rvPreffered,
//                fragmentManager,
//                make_bottom_dialogvalues,
//                model_dialogvalues_two,
//                mydata?.prefferedVehicle!!
//            )
//            rvPreffered?.adapter = adapter
//                for(i in 0 until mydata?.prefferedVehicle?.size!! )
//            {
//
//
//                val row2: View? = rvPreffered?.getLayoutManager()?.findViewByPosition(0)
//                val make = row2?.findViewById<View>(R.id.tvMake) as? TextView
//                val model = row2?.findViewById<View>(R.id.tvModel) as? TextView
//                make?.setText(mydata?.prefferedVehicle?.get(i)?.make.toString())
//                model?.setText(mydata?.prefferedVehicle?.get(i)?.model.toString())
//                Toast.makeText(activity,mydata?.prefferedVehicle?.get(i)?.make,Toast.LENGTH_SHORT).show()
//            }
        }


//        var data: MutableList<String> = ArrayList<String>()
//        data.add("")
//        rvPreffered?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        val adapter = PrefferedAdapter(data, activity, rvPreffered,fragmentManager,make_bottom_dialogvalues,model_dialogvalues_two)
//        rvPreffered?.adapter = adapter

        tvVariant.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getVariants(modelid!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvMake1.setOnClickListener {
            presenter.getMake2("1")
        }
        tvModel1.setOnClickListener {
            presenter.getModel2(make1id,"1")
        }

        tvMake2.setOnClickListener {
            presenter.getMake2("2")
        }
        tvModel2.setOnClickListener {
            presenter.getModel2(makeeidTwo,"2")
        }


        tvModel.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getModel(makeid)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvMake.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getMake()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvYear.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getYear()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        tvAddMore.setOnClickListener {
            addmorestatus="1"
            llTwo.visibility=View.VISIBLE
        }

        tvNext.setOnClickListener {
            val arrayOne = JSONArray()
            val objone = JSONObject()
            try {
                objone.put("MakeId", makeid)
                objone.put("ModelId", modelid)
                objone.put("VariantId", variantid)
                objone.put("Year", year)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
            arrayOne.put(objone)

            val array = JSONArray()

            val obj = JSONObject()
            val obj2 = JSONObject()
                try {
                    obj.put("MakeId", make1id)
                    obj.put("ModelId", model1id)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                array.put(obj)
            if (addmorestatus.equals("1")) {
                try {
                    obj2.put("MakeId", makeeidTwo)
                    obj2.put("ModelId", modelidTwo)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
                array.put(obj2)
            }
            println("uuu .....makeeidTwo...."+makeeidTwo)
            println("uuu .....modelidTwo...."+modelidTwo)
            println("uuu .....make1id...."+make1id)
            println("uuu .....model1id...."+model1id)

//            if(valid())
//            {
            var confirmfragment =
                EditLeadFourFragment.newInstance(
                    name!!, email!!, phone!!, pincode!!, state!!, district!!, location!!,
                    employment!!,
                    income!!,
                    edtBudget.text.toString()!!!!,
                    leaddate!!,
                    leadloginid!!,
                    arrayOne, array, plantobuyid, lastname, mydata, source, subsource
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    EditLeadFourFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
            // }


        }

        edtBudget.setOnClickListener {
            presenter.getBudjet()
        }

    }

    override fun IncomeSuccess(data: List<IncomeList>) {
        val classdialog = BudjetDialog(data)
        classdialog.setBusId(budjet_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    private fun getYear() {
        presenter.getYear()
    }

    private fun valid(): Boolean {
        if (makeid.length == 0) {
            Toast.makeText(activity, "Select Make", Toast.LENGTH_SHORT).show()
        } else if (modelid.length == 0) {
            Toast.makeText(activity, "Select Model", Toast.LENGTH_SHORT).show()
        } else if (variantid.length == 0) {
            Toast.makeText(activity, "Select Variant", Toast.LENGTH_SHORT).show()
        } else return true

        return false
    }

    private fun getMake() {
        presenter.getMake()
    }

    private fun getModel(makeid: String) {
        presenter.getModel(makeid)
    }

    private fun getVariants(modelid: String) {
        presenter.getVariant(modelid!!);
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
            param13: String,
            param14: String?,
            param15: ViewLead,
            param16: String,
            param17: String?
        ) =
            EditLeadThreeFragment().apply {
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
                    putString(ARG_PARAM13, param13)
                    putString(ARG_PARAM14, param14)
                    putString(ARG_PARAM16, param16)
                    putString(ARG_PARAM17, param17)
                    mydata = param15
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

    override fun Make(data: List<MakeList>) {
        val makelist = ArrayList<String>()
        val makeidlist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                makelist.add(data.get(i)?.make!!)
                makeidlist.add(data.get(i)?.makeId.toString())
            }
        }
        val classdialog = MakeDialog(data, makelist, makeidlist)
        classdialog.setBusId(make_dialogvalues)
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
        if(type.equals("1")) {
            val classdialog = MakeDialogTwo(data, makelist, makeidlist)
            classdialog.setBusId(make_dialogvalues_two)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }else{
            val classdialog = MakeDialogThree(data, makelist, makeidlist)
            classdialog.setBusId(make_dialogvalues_three)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        }

    }

    override fun VariantSuccess(data: List<VariantList>) {
        val classdialog = VariantDialog(data)
        classdialog.setBusId(variant_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun loginError(msg: String) {

    }

    override fun ModelSuccess(data: List<ModelList>) {
        val classdialog = ModelDialog(data)
        classdialog.setBusId(model_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    override fun ModelSuccessTwo(data: List<ModelList>,type:String) {
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

    override fun YearSuccess(data: List<YearList>) {
        val classdialog = YearDialog(data)
        classdialog.setBusId(year_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }
}
