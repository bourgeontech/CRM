package com.git.crm.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.BudjetDialogAdapter
import com.git.crm.Pojo.IncomeList

import com.git.crm.R
import kotlinx.android.synthetic.main.fragment_type_list.*


class BudjetDialog(val doctorList: List<IncomeList>) : DialogFragment() {

    lateinit var onBusidval: OnBusIdvalue

    public interface OnBusIdvalue {
        fun onBusId(name: String)
    }

    fun setBusId(busdetails: OnBusIdvalue) {
        onBusidval = busdetails
    }


    private lateinit var busdetailsval: BudjetDialogAdapter.OnBusdetails;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater!!.inflate(R.layout.fragment_type_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeading.setText("Select Budjet")
        busdetailsval = object : BudjetDialogAdapter.OnBusdetails {
            override fun onBus(name: String) {
                onBusidval.onBusId(name)
                dismiss()
            }
        }
        rvList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = BudjetDialogAdapter(activity, fragmentManager,doctorList)
        adapter.setBusInterface(busdetailsval)
        rvList.adapter = adapter
    }


}