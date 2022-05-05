package com.git.crm.ViewLead

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.git.crm.Adapter.CurrentAdapter
import com.git.crm.Adapter.PrefferAdapter
import com.git.crm.Adapter.SuggestAdapter
import com.git.crm.EditLeadOne.EditLeadOneFragment
import com.git.crm.MainActivity
import com.git.crm.Pojo.ViewLead
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_view_lead.*
import kotlinx.android.synthetic.main.fragment_view_lead.ivback
import kotlinx.android.synthetic.main.fragment_view_lead.progress
import kotlinx.android.synthetic.main.fragment_view_lead.tvName

private const val ARG_PARAM1 = "param1"


class ViewLeadFragment : Fragment(), ViewLeadView {

    private lateinit var mydata: ViewLead
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private var param1: String? = null
    private val presenter = ViewLeadPresenter(this, VieLeadInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_view_lead, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        (activity as MainActivity?)!!.heading("Lead Details")
        (activity as MainActivity?)!!.setVisibility("")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
            presenter.lead(userId!!, param1!!)
        } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){

        }
        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        ivedit.setOnClickListener {
            if (mydata != null) {
                var confirmfragment =
                    EditLeadOneFragment.newInstance(
                        mydata
                    )
                fragmentManager?.beginTransaction()
                    ?.replace(
                        R.id.fl_container,
                        confirmfragment,
                        EditLeadOneFragment.javaClass.name
                    )
                    ?.addToBackStack("")?.commit()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ViewLeadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
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

    override fun report(data: ViewLead?) {
        mydata = data!!
        tvName.text = data?.data?.get(0)?.name + " " + data?.data?.get(0)?.lastName
        tvContact.text = "Contact : " + data?.data?.get(0)?.contact
        tvEmail.text = "Email : " + data?.data?.get(0)?.emailId
        tvPincode.text = "Pincode : " + data?.data?.get(0)?.pincode
        tvBudjet.text = "Budjet : " + data?.data?.get(0)?.budget
        tvAnnualIncome.text = "Income : " + data?.data?.get(0)?.anualIncome
        tvLeadDate.text = "Lead Date : " + data?.data?.get(0)?.leadDate
        tvRemark.text = "Remark : " + data?.data?.get(0)?.remark
        if (data?.data?.get(0)?.location == null) {
            tvLocation.text = "Location : NA"
        } else
            tvLocation.text = "Location : " + data?.data?.get(0)?.location

        tvState.text = "State : " + data?.data?.get(0)?.state
        tvDistrict.text = "District : " + data?.data?.get(0)?.district
        tvBranch.text = "Branch : " + data?.data?.get(0)?.branch
        tvJob.text = "Employment : " + data?.data?.get(0)?.employment
        tvPlantobuy.text = "Plan To Buy : " + data?.data?.get(0)?.plantoBuy

        try {
            rvCurrent.adapter = null
            rvCurrent?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = CurrentAdapter(
                data?.currentVehicle,
                requireActivity(),
                requireFragmentManager()

            )
            rvCurrent?.adapter = adapter
        } catch (e: Exception) {
        }

        try {
            rvSuggest.adapter = null
            rvSuggest?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = SuggestAdapter(
                data?.suggestedVehicle,
                requireActivity(),
                requireFragmentManager()

            )
            rvSuggest?.adapter = adapter
        } catch (e: Exception) {
        }

        try {
            rvPreffered.adapter = null
            rvPreffered?.layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            val adapter = PrefferAdapter(
                data?.prefferedVehicle,
                requireActivity(),
                requireFragmentManager()

            )
            rvPreffered?.adapter = adapter
        } catch (e: Exception) {
        }
    }

    override fun loginError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}