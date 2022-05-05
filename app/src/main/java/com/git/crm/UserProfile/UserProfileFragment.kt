package com.git.crm.UserProfile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.git.crm.MainActivity
import com.git.crm.Pojo.UserProfilePojo
import com.git.crm.R
import com.git.crm.Utils.Constants
import com.git.crm.Utils.NetworkConnection
import com.git.crm.Utils.PreferenceRequestHelper
import kotlinx.android.synthetic.main.fragment_user_profile.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserProfileFragment : Fragment(), UserProfileView {
    private var phone: String? = ""
    private var email: String? = ""
    private var branch: String? = ""
    private var userName: String? = ""
    private var userId: String? = ""
    private lateinit var preferenceObj: PreferenceRequestHelper
    private val presenter = UserProfilePresenter(this, UserProfileInteractor())

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_user_profile, container, false)
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)!!.heading("Status")
        (activity as MainActivity?)!!.setVisibility("")
        preferenceObj = PreferenceRequestHelper(activity)
        userId = preferenceObj.getStringValue(Constants.PRES_ID, "")
        userName = preferenceObj.getStringValue(Constants.PRES_NAME, "")
        email = preferenceObj.getStringValue(Constants.PRES_EMAIL, "")
        phone = preferenceObj.getStringValue(Constants.PRES_MOBILE, "")
        branch = preferenceObj.getStringValue(Constants.PRES_Branch, "")
        try {
            if (NetworkConnection.isNetworkAvailable(requireActivity())) {
                presenter.lead(userId!!)
            } else Toast.makeText(activity, "No internet", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UserProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
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

    override fun report(data: UserProfilePojo?) {
        try {
            if (data?.data?.get(0)?.firstName?.length == 0) {
                tvName.text = userName

            } else {
                tvName.text = data?.data?.get(0)?.firstName + " " + data?.data?.get(0)?.lastName
            }
            if (data?.data?.get(0)?.mobileNumber?.length!! > 0) {
                tvContact.text = data?.data?.get(0)?.mobileNumber
            } else {
                tvContact.text = phone
            }

            if (data?.data?.get(0)?.eMail?.length!! > 0) {
                tvEmail.text = data?.data?.get(0)?.eMail
            } else {
                tvEmail.text = email
            }

            if (data?.data?.get(0)?.branch?.length!! > 0) {
                tvBranch.text = data?.data?.get(0)?.branch
            } else {
                tvBranch.text = branch
            }

        } catch (e: Exception) {

        }

    }

    override fun loginError(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
}