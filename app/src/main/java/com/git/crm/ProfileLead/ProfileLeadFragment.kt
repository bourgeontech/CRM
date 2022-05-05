package com.git.crm.ProfileLead


import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.git.crm.Deals.DealsFragment
import com.git.crm.Followuptask.FollowUpTask
import com.git.crm.MainActivity
import com.git.crm.Pojo.ProfileList
import com.git.crm.R
import com.git.crm.Remarks.RemarkFragment
import com.git.crm.Status.StatusFragment
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_profile_lead.*
import kotlinx.android.synthetic.main.fragment_profile_lead.ivback
import kotlinx.android.synthetic.main.fragment_profile_lead.progress
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"
private const val ARG_PARAM5 = "param5"

class ProfileLeadFragment : Fragment(), ProfileLeadView {
    private var lastname: String?=""
    private var emailid: String = ""
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = ProfileLeadPresenter(this, ProfileLeadInteractor())
    private var param1: String? = null
    private var param2: String? = null
    private var param3: String? = null
    private var param5: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getString(ARG_PARAM3)
            param5 = it.getString(ARG_PARAM5)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile_lead, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        (activity as MainActivity?)!!.setVisibility("")
        (activity as MainActivity?)!!.heading("Lead Profile")
        ivback.setOnClickListener {
            fragmentManager?.popBackStack()
        }
        tvCall.setOnClickListener {
            if (param3?.length!! > 0) {
                val intent = Intent(Intent.ACTION_CALL)
                intent.data = Uri.parse("tel:" + param3)
                try {
                    startActivity(intent)
                } catch (e: Exception) {
//                Toast.makeText(
//                    activity,
//                    e.toString(),
//                    Toast.LENGTH_SHORT
//                ).show()
                }
            }

        }
        tvEmail.setOnClickListener {
            if (emailid.length > 0) {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "message/rfc822"
                i.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailid))
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email")
                i.putExtra(Intent.EXTRA_TEXT, "body of email")
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."))
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(
                        activity,
                        "There are no email clients installed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        try {
            tvMobile.text = param3
        } catch (e: Exception) {

        }
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.report(userId!!, param1!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
        cvstatus.setOnClickListener {

            var confirmfragment =
                StatusFragment.newInstance(
                    param1!!, param2, param5,lastname
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    StatusFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()

        }

        cvfollowup.setOnClickListener {

            var confirmfragment =
                FollowUpTask.newInstance(
                    param1!!
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    FollowUpTask.javaClass.name
                )
                ?.addToBackStack("")?.commit()

        }

        cvRemarks.setOnClickListener {
            var confirmfragment =
                RemarkFragment.newInstance(
                    param1!!, param2!!, param5,lastname
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    RemarkFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
        }

        cvDeals.setOnClickListener {
            var confirmfragment =
                DealsFragment.newInstance(
                    param1!!, param2!!, param5,lastname
                )
            fragmentManager?.beginTransaction()
                ?.replace(
                    R.id.fl_container,
                    confirmfragment,
                    DealsFragment.javaClass.name
                )
                ?.addToBackStack("")?.commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(
            param1: String,
            param2: String,
            param3: String,
            param4: String,
            param5: String
        ) =
            ProfileLeadFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    putString(ARG_PARAM5, param5)
                    //putString(ARG_PARAM2, param2)
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

    override fun report(id: List<ProfileList>?) {
        try {
            if (id?.get(0)?.status?.toString()
                    .equals("Interested") || id?.get(0)?.status?.toString().equals("Converted")
            ) {
                cvfollowup.visibility = View.GONE
            } else {
                cvfollowup.visibility = View.VISIBLE
            }
        } catch (e: Exception) {

        }
        try {
            tvName.text = id?.get(0)?.customerName + " " + id?.get(0)?.customerLastName
            lastname=id?.get(0)?.customerLastName
            tvAdded.text = "Lead Added By : " + id?.get(0)?.createdUserName
            emailid = id?.get(0)?.customerEmailId!!
            if (id?.get(0)?.statusDate?.length!! > 0) {
                tvStatusDate.text = parseDateToddMMyyyy(id?.get(0)?.statusDate)
                tvStatusDate.visibility = View.VISIBLE

            } else {
                tvStatusDate.visibility = View.GONE

            }
            if (id?.get(0)?.status?.length!! > 0) {
                tvStatus.text = id?.get(0)?.status
                tvStatus.visibility = View.VISIBLE
            } else {

                tvStatus.visibility = View.GONE
            }

            if (id?.get(0)?.followUpDate?.length!! > 0) {
                tvFollowDate.text = parseDateToddMMyyyy(id?.get(0)?.followUpDate)
                tvFollowDate.visibility = View.VISIBLE

            } else {
                tvFollowDate.visibility = View.GONE

            }
            if (id?.get(0)?.followUp?.length!! > 0) {
                tvFollow.text = id?.get(0)?.followUp
                tvFollow.visibility = View.VISIBLE
            } else {

                tvFollow.visibility = View.GONE
            }
            if (id?.get(0)?.deal?.length!! > 0) {
                tvDeal.text = "Added deal is :" + id?.get(0)?.deal
                tvDeal.visibility = View.VISIBLE
            } else {

                tvDeal.visibility = View.GONE
            }

            if (id?.get(0)?.dealDate?.length!! > 0) {
                tvDealDate.text = parseDateToddMMyyyy(id?.get(0)?.dealDate)
                tvDealDate.visibility = View.VISIBLE
            } else {

                tvDealDate.visibility = View.GONE
            }

//        if(id?.get(0)?.remark?.length!!>0)
//        {
//            tvRemarksDate.text=id?.get(0)?.remark
//            tvRemarksDate.visibility=View.VISIBLE
//
//        }else {
//            tvRemarksDate.visibility = View.GONE
//
//        }
            if (id?.get(0)?.remark?.length!! > 0) {
                tvRemarks.text = id?.get(0)?.remark
                tvRemarks.visibility = View.VISIBLE
            } else {

                tvRemarks.visibility = View.GONE
            }

        }catch (e:Exception){

        }
    }

    override fun loginError(msg: String) {
        try {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }

    }

    fun parseDateToddMMyyyy(time: String?): String? {
        // val inputPattern = "yyyy-MM-dd hh:mm:ss"
        val inputPattern = "MMM dd yyyy hh:mm aaa"
        val outputPattern = "dd-MMM-yyyy  hh:mm aaa"
        val inputFormat = SimpleDateFormat(inputPattern)
        val outputFormat = SimpleDateFormat(outputPattern)
        var date: Date? = null
        var str: String? = null
        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return str
    }
}