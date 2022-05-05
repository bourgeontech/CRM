package com.git.crm.Dialogs

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.git.crm.Adapter.DistrictDialogAdapter
import com.git.crm.Pojo.DistrictList
import com.git.crm.R
import kotlinx.android.synthetic.main.fragment_type_list.*


class DistrictDialog(
    val doctorList: List<DistrictList>,
     var districtlist: ArrayList<String>,
    var districtidlist: ArrayList<String>
) : DialogFragment() {

    private lateinit var adapter: DistrictDialogAdapter
    lateinit var onBusidval: OnBusIdvalue

    public interface OnBusIdvalue {
        fun onBusId(id: String,name: String)
    }

    fun setBusId(busdetails: OnBusIdvalue) {
        onBusidval = busdetails
    }


    private lateinit var busdetailsval: DistrictDialogAdapter.OnBusdetails;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater!!.inflate(R.layout.fragment_type_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeading.setText("Select District")
        busdetailsval = object : DistrictDialogAdapter.OnBusdetails {
            override fun onBus(id: String,name: String) {
                onBusidval.onBusId(id,name)
                dismiss()
            }
        }
        edSearch.visibility=View.VISIBLE
        edSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                //after the change calling the method and passing the search input
                filter(editable.toString())
            }
        })
        rvList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
         adapter = DistrictDialogAdapter(activity, fragmentManager,doctorList,districtlist,districtidlist)
        adapter.setBusInterface(busdetailsval)
        rvList.adapter = adapter
    }

    private fun filter(text: String) {
        //new array list that will hold the filtered data
        //  doc2=doctorList
        println(text+"........ccc.........text")
        if(text.length>0) {
            val filterlist = ArrayList<String>()
            val filterlist2 = ArrayList<String>()
            for (i in 0 until districtlist.size) {
                //if the existing elements contains the search input
                if (districtlist?.get(i).toLowerCase()?.contains(text.toLowerCase())) {
                    println(districtlist?.get(i)+"........ccc.........text")
                    filterlist.add(districtlist?.get(i))
                    filterlist2.add(districtidlist?.get(i))
                }

            }

            //calling a method of the adapter class and passing the filtered list
            adapter.filterList(filterlist, filterlist2)
        }else
        {
            adapter.filterList(districtlist, districtidlist)
        }
    }


}