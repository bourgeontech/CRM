package com.git.crm.CustomerDetailsThree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.PrefferedAdapter
import com.git.crm.CustomerDetailsFour.CustomerDetailsFragmentFour
import com.git.crm.Dialogs.*
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
private const val ARG_PARAM15 = "param15"
private const val ARG_PARAM16 = "param16"

/**
 * A simple [Fragment] subclass.
 * Use the [CustomerDetailsFragmentThree.newInstance] factory method to
 * create an instance of this fragment.
 */
class CustomerDetailsFragmentThree : Fragment(), CustomerPageThreeView {
    private var make1name: String=""
    private var model1name: String=""
    private var make2name: String=""
    private var model2name: String=""
    private var modelidTwo: String = ""
    private lateinit var model_dialogvalues_three: ModelDialogThree.OnBusIdvalue
    private var makeeidTwo: String = ""
    private lateinit var make_dialogvalues_three: MakeDialogThree.OnBusIdvalue
    private var addmorestatus: String = "0"
    private var modelidOne: String = ""
    private var makeidOne: String = ""
    private var make: String = ""
    private var model: String = ""
    private var variantname: String = ""
    private lateinit var budjet_dialogvalues: BudjetDialog.OnBusIdvalue
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

    private lateinit var make_bottom_dialogvalues: PrefferedAdapter.OnBusdetails
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
    private var sourceid: String? = null
    private var subsourceid: String? = null
    private val presenter = CustomerPageThreePresenter(this, CustomerPageThreeInteractor())
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
            sourceid = it.getString(ARG_PARAM15)
            subsourceid = it.getString(ARG_PARAM16)

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

    override fun onResume() {
        super.onResume()
        tvVariant.text = variantname
        edtBudget.text = budjet
        tvYear.text = year
        tvModel.text = model
        tvMake.text = make
        tvModel2.text=model2name
        tvMake2.text=make2name
        tvMake1.text=make1name
        tvModel1.text=model1name
        if(addmorestatus.equals("1"))
        {
            llTwo.visibility=View.VISIBLE
        }else
        {
            llTwo.visibility=View.GONE
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.setVisibility("")
        variant_dialogvalues = object : VariantDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvVariant.text = name
                variantname = name
                variantid = id
                tvYear.text = ""
                year = ""
            }

        }
        budjet_dialogvalues = object : BudjetDialog.OnBusIdvalue {
            override fun onBusId(name: String) {
                edtBudget.text = name
                budjet = name
            }

        }
        model_dialogvalues = object : ModelDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModel.text = name
                modelid = id
                model = name

                tvVariant.text = ""
                variantid = ""

                tvYear.text = ""
                year = ""


            }

        }
        make_dialogvalues = object : MakeDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMake.text = name
                makeid = id
                make = name

                tvModel.text = ""
                modelid = ""

                tvVariant.text = ""
                variantid = ""

                tvYear.text = ""
                year = ""
            }

        }
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

        year_dialogvalues = object : YearDialog.OnBusIdvalue {
            override fun onBusId(name: String) {
                tvYear.text = name
                year = name
            }

        }

        make_dialogvalues_two = object : MakeDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvMake1.text = name
                makeidOne = id
                make1name=name

//                val row2: View? =
//                    rvPreffered?.getLayoutManager()?.findViewByPosition(selectposition)
//                val make = row2?.findViewById<View>(R.id.tvMake) as TextView
//                val makeid = row2?.findViewById<View>(R.id.tvMakeId) as TextView
//                make.text = name
//                makeid.text = id
//
//                val model = row2?.findViewById<View>(R.id.tvModel) as TextView
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as TextView
//                modelid.text = ""
//                model.text = ""
            }

        }
        model_dialogvalues_two = object : ModelDialogTwo.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvModel1.text = name
                modelidOne = id
                model1name=name
//                val row2: View? =
//                    rvPreffered?.getLayoutManager()?.findViewByPosition(selectposition)
//                val model = row2?.findViewById<View>(R.id.tvModel) as TextView
//                val modelid = row2?.findViewById<View>(R.id.tvModelId) as TextView
//                modelid.text = id
//                model.text = name
            }

        }
//        make_bottom_dialogvalues = object : PrefferedAdapter.OnBusdetails {
//            override fun onBus(position: Int, type: String) {
//                selectposition = position
//                if (type.equals("make")) {
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getMake2()
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                } else {
//                    val row2: View? = rvPreffered?.getLayoutManager()?.findViewByPosition(position)
//                    val make = row2?.findViewById<View>(R.id.tvMake) as TextView
//                    val makeid = row2?.findViewById<View>(R.id.tvMakeId) as TextView
//                    if (NetworkConnection.isNetworkAvailable(requireActivity())) {
//                        presenter.getModel2(makeid.text.toString())
//                    } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
//                }
//
//            }
//
//        }
//        var data: MutableList<String> = ArrayList<String>()
//        data.add("")
//        rvPreffered?.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        val adapter = PrefferedAdapter(
//            data,
//            activity,
//            rvPreffered,
//            fragmentManager,
//            make_bottom_dialogvalues,
//            model_dialogvalues_two
//        )
//        rvPreffered?.adapter = adapter

        tvMake1.setOnClickListener {
            presenter.getMake2("1")
        }
        tvModel1.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                if (makeidOne.length > 0)
                    presenter.getModel2(makeidOne, "1")
                else Toast.makeText(activity, "Select Make", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvMake2.setOnClickListener {
            presenter.getMake2("2")
        }
        tvModel2.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                if (makeeidTwo.length > 0)
                    presenter.getModel2(makeeidTwo, "2")
                else Toast.makeText(activity, "Select Make", Toast.LENGTH_SHORT).show()
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }
        tvAddMore.setOnClickListener {
            addmorestatus = "1"
            llTwo.visibility = View.VISIBLE
            tvAddMore.visibility = View.GONE
        }

        tvVariant.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                getVariants(modelid)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
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

        tvNext.setOnClickListener {
            if (valid()) {
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
                obj.put("MakeId", makeidOne)
                obj.put("ModelId", modelidOne)
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



            var confirmfragment =
                CustomerDetailsFragmentFour.newInstance(
                    name!!, email!!, phone!!, pincode!!, state!!, district!!, location!!,
                    employment!!,
                    income!!,
                    edtBudget.text.toString()!!,
                    leaddate!!,
                    leadloginid!!,
                    arrayOne, array, plantobuyid, lastname, sourceid, subsourceid
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    CustomerDetailsFragmentFour.javaClass.name
                )
                ?.addToBackStack("")?.commit()
             }


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
        if (makeidOne.length == 0) {
           // Toast.makeText(activity, "Select Preffered Make", Toast.LENGTH_SHORT).show()
            tvMake1.setError("Select Make")
        } else if (modelidOne.length == 0) {
            tvModel1.setError("Select Model")
           // Toast.makeText(activity, "Select Preffered Model", Toast.LENGTH_SHORT).show()
        } else if (addmorestatus.equals("1")&&makeeidTwo.length == 0) {
            tvMake2.setError("Select Make")
            //Toast.makeText(activity, "Select Preffered Make", Toast.LENGTH_SHORT).show()
        } else if (addmorestatus.equals("1")&&modelidTwo.length == 0) {
            tvModel2.setError("Select Model")
           // Toast.makeText(activity, "Select Preffered Model", Toast.LENGTH_SHORT).show()
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
        presenter.getVariant(modelid);
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
            param15: String?,
            param16: String?
        ) =
            CustomerDetailsFragmentThree().apply {
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
                    putString(ARG_PARAM15, param15)
                    putString(ARG_PARAM16, param16)
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

    override fun Make2(data: List<MakeList>, type: String) {
        val makelist = ArrayList<String>()
        val makeidlist = ArrayList<String>()
        if (data.size > 0) {
            for (i in 0 until data.size) {
                makelist.add(data.get(i)?.make!!)
                makeidlist.add(data.get(i)?.makeId.toString())
            }
        }
        if (type.equals("1")) {
            val classdialog = MakeDialogTwo(data, makelist, makeidlist)
            classdialog.setBusId(make_dialogvalues_two)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        } else {
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

    override fun ModelSuccessTwo(data: List<ModelList>, type: String) {
        if (type.equals("1")) {
            val classdialog = ModelDialogTwo(data)
            classdialog.setBusId(model_dialogvalues_two)
            val fm = fragmentManager?.beginTransaction()
            classdialog.show(fm!!, "name")
        } else {
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
