package com.git.crm.Status

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.StatusAdapter
import com.git.crm.Dashboard.DashboardFragment
import com.git.crm.Dialogs.StatusDialog
import com.git.crm.MainActivity
import com.git.crm.Pojo.StatusList
import com.git.crm.ProfileLead.ProfileLeadFragment
import com.git.crm.R
import com.git.crm.Report.ReportFragment
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_customer_details_four.*
import kotlinx.android.synthetic.main.fragment_status.*
import kotlinx.android.synthetic.main.fragment_status.edtRemark
import kotlinx.android.synthetic.main.fragment_status.ivback
import kotlinx.android.synthetic.main.fragment_status.progress
import kotlinx.android.synthetic.main.fragment_status.tvCount

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM4 = "param4"

class StatusFragment : Fragment(), StatusView {
    private var statusid: String = ""
    private lateinit var status_dialogvalues: StatusDialog.OnBusIdvalue
    private lateinit var adapter: StatusAdapter
    private val presenter = StatusPresenter(this, StatusInteractor())
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param4: String? = null
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param4 = it.getString(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_status, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.heading("Status")
        (activity as MainActivity?)!!.setVisibility("")
        status_dialogvalues = object : StatusDialog.OnBusIdvalue {
            override fun onBusId(id: String, name: String) {
                tvStatus.text = name
                statusid = id
            }

        }
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.report(userId!!, param1!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        tvStatus.setOnClickListener {
            getStatus()
        }
        try {
        tvName.text = "Lead Name : " + param2+" "+param4
        tvDate.text = "Lead Date : " + param3
        } catch (e: Exception) {

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
        tvSave.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                if (statusid.length > 0) {
                    if (!(statusid.equals("4")))
                        presenter.savestatus(
                            param1!!,
                            userId!!,
                            statusid,
                            edtRemark.text.toString()
                        )
                    else {
                        if (edtRemark.text.length > 0) {
                            presenter.savestatus(
                                param1!!,
                                userId!!,
                                statusid,
                                edtRemark.text.toString()
                            )
                        } else {
                            edtRemark.setError("Enter Remark")
                        }
                    }
                }


            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()


        }

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }

    }

    private fun getStatus() {
        var data: MutableList<String> = ArrayList<String>()
        // data.add("New Lead")
        data.add("Interested")
        data.add("Hot")
        data.add("Cold")
        data.add("Not Intersted")
        val classdialog = StatusDialog(data)
        classdialog.setBusId(status_dialogvalues)
        val fm = fragmentManager?.beginTransaction()
        classdialog.show(fm!!, "name")
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String?, param3: String?, param4: String?) =
            StatusFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM4, param4)
                }
            }
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

    override fun report(list: List<StatusList>?) {
        try {
            rvStatus.adapter = null
            rvStatus?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = StatusAdapter(
                list,
                requireActivity(),
                requireFragmentManager()

            )
            rvStatus?.adapter = adapter

//            setRVLayoutManager()
//            setRVScrollListener()
        } catch (e: java.lang.Exception) {
        }
    }

    override fun loginError(msg: String) {
        try {
            rvStatus.adapter = null
        } catch (e: Exception) {

        }

        // Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()

    }

    override fun onsave(message: String) {
        try {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        fragmentManager?.beginTransaction()?.replace(R.id.fl_container, DashboardFragment())
            ?.addToBackStack("")?.commitAllowingStateLoss()

    }
}