package com.git.crm.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.YearDialogAdapter
import com.git.crm.Pojo.YearList
import com.git.crm.R
import kotlinx.android.synthetic.main.fragment_type_list.*


class YearDialog(val doctorList: List<YearList>) : DialogFragment() {

    lateinit var onBusidval: OnBusIdvalue

    public interface OnBusIdvalue {
        fun onBusId(name: String)
    }

    fun setBusId(busdetails: OnBusIdvalue) {
        onBusidval = busdetails
    }


    private lateinit var busdetailsval: YearDialogAdapter.OnBusdetails;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        val view = inflater!!.inflate(R.layout.fragment_type_list_two, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtHeading.setText("Select Year")
        busdetailsval = object : YearDialogAdapter.OnBusdetails {
            override fun onBus(name: String) {
                onBusidval.onBusId(name)
                dismiss()
            }
        }
        rvList.setLayoutManager(GridLayoutManager(activity, 3))
      //  rvList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        val adapter = YearDialogAdapter(activity, fragmentManager,doctorList)
        adapter.setBusInterface(busdetailsval)
        rvList.adapter = adapter
    }


}