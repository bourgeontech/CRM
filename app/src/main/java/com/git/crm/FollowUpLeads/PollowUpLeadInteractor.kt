package com.git.crm.FollowUpLeads

import androidx.collection.ArrayMap
import com.git.crm.Pojo.GetLeadPojo
import com.git.crm.Pojo.GetLeadsPojo
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PollowUpLeadInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: MutableList<GetLeadsPojo?>
        )
        fun onSuccessMore(
            id: MutableList<GetLeadsPojo?>
        )
        fun onFailureMore(msg: String)
        fun onFailure(msg: String)
    }

    fun lead(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadStatus"] = ""
        jsonParams["FromDate"] = FromDate
        jsonParams["ToDate"] = ToDate
        jsonParams["Status"] = "assigned"
        jsonParams["PageIndex"] = PageIndex
        jsonParams["PageSize"] = PageSize
        jsonParams["Keyword"] = Keyword

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<GetLeadPojo> = ApiClient.getClient.get_leads_followup(body)
        call.enqueue(object : Callback<GetLeadPojo> {
            override fun onFailure(call: Call<GetLeadPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<GetLeadPojo>,
                response: Response<GetLeadPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccess(
                            response.body()?.data!!)
                    } else {
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

    fun leadmore(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadStatus"] = ""
        jsonParams["FromDate"] = FromDate
        jsonParams["ToDate"] = ToDate
        jsonParams["Status"] = "added"
        jsonParams["PageIndex"] = PageIndex
        jsonParams["PageSize"] = PageSize
        jsonParams["Keyword"] = Keyword
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<GetLeadPojo> = ApiClient.getClient.get_leads_followup(body)
        call.enqueue(object : Callback<GetLeadPojo> {
            override fun onFailure(call: Call<GetLeadPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<GetLeadPojo>,
                response: Response<GetLeadPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSuccessMore(
                            response.body()?.data!!)
                    } else {
                        listener.onFailureMore(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }
}


