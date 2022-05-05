package com.git.crm.Remarks

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.RemarkAdapter
import com.git.crm.MainActivity
import com.git.crm.Pojo.RemarkList
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_customer_details_four.*
import kotlinx.android.synthetic.main.fragment_remark.*
import kotlinx.android.synthetic.main.fragment_remark.edtRemark
import kotlinx.android.synthetic.main.fragment_remark.ivback
import kotlinx.android.synthetic.main.fragment_remark.progress
import kotlinx.android.synthetic.main.fragment_remark.rvStatus
import kotlinx.android.synthetic.main.fragment_remark.tvCount
import kotlinx.android.synthetic.main.fragment_remark.tvDate
import kotlinx.android.synthetic.main.fragment_remark.tvName
import kotlinx.android.synthetic.main.fragment_remark.tvSave
import java.lang.Exception


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM5 = "param5"
private const val ARG_PARAM3 = "param3"

class RemarkFragment : Fragment(), RemarkView {
    val presenter = RemarkPresenter(this, RemarkInteractor())

    private var param1: String? = null
    private var param2: String? = null
    private var param5: String? = null
    private var param3: String? = null
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param5 = it.getString(ARG_PARAM5)
            param3 = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_remark, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.heading("Remark")
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        (activity as MainActivity?)!!.setVisibility("")
        tvName.text = "Lead Name : " + param2+" "+param3
        tvDate.text = "Lead Date : " + param5
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.Remark(userId!!, param1!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        tvSave.setOnClickListener {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.saveRemark(param1!!, userId!!, edtRemark.text.toString())
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }

        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
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
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String, param5: String?, param3: String?) =
            RemarkFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM5, param5)
                    putString(ARG_PARAM3, param3)
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

    override fun report(id: List<RemarkList>?) {
        if (id?.size!! > 0) {
            rvStatus.visibility = View.VISIBLE
            tvPre.visibility = View.VISIBLE
            try {
                rvStatus.adapter = null
                rvStatus?.layoutManager =
                    LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
                val adapter = RemarkAdapter(
                    id,
                    requireActivity(),
                    requireFragmentManager()

                )
                rvStatus?.adapter = adapter

//            setRVLayoutManager()
//            setRVScrollListener()
            } catch (e: java.lang.Exception) {
            }

        } else {
            rvStatus.visibility = View.GONE
            tvPre.visibility = View.GONE
        }

    }

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    override fun onsave(message: String) {
        try {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {

        }
        val fm: FragmentManager = requireActivity()!!.supportFragmentManager
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }
}