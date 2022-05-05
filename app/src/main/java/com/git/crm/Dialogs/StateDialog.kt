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
import com.git.crm.Adapter.StateDialogAdapter
import com.git.crm.Pojo.StateList
import com.git.crm.R
import kotlinx.android.synthetic.main.fragment_type_list.*


class StateDialog(
    val doctorList: MutableList<StateList?>,
    var statelist: ArrayList<String>,
    var stateidlist: ArrayList<String>
) : DialogFragment() {

    private lateinit var doc2: MutableList<StateList?>
    private lateinit var adapter: StateDialogAdapter
    lateinit var onBusidval: OnBusIdvalue
    var filternames : MutableList<StateList?> = arrayListOf()
    public interface OnBusIdvalue {
        fun onBusId(id: String,name: String)
    }

    fun setBusId(busdetails: OnBusIdvalue) {
        onBusidval = busdetails
    }


    private lateinit var busdetailsval: StateDialogAdapter.OnBusdetails;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater!!.inflate(R.layout.fragment_type_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeading.setText("Select State")
        busdetailsval = object : StateDialogAdapter.OnBusdetails {
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
         adapter = StateDialogAdapter(activity, fragmentManager,doctorList,statelist,stateidlist)
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
            statelist
            for (i in 0 until stateidlist.size) {
                //if the existing elements contains the search input
                if (statelist?.get(i).toLowerCase()?.contains(text.toLowerCase())) {
                    println(statelist?.get(i)+"........ccc.........text")
                    filterlist.add(statelist?.get(i))
                    filterlist2.add(stateidlist?.get(i))
                }

            }

            //calling a method of the adapter class and passing the filtered list
            adapter.filterList(filterlist, filterlist2)
        }else
        {
            adapter.filterList(statelist, stateidlist)
        }
    }


}


