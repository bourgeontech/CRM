package com.git.crm.Leads

import androidx.collection.ArrayMap
import com.git.crm.Pojo.GetLeadPojo
import com.git.crm.Pojo.GetLeadsPojo
import com.git.crm.Pojo.SendPojo
import com.git.crm.Pojo.ViewLead
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LeadInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: MutableList<GetLeadsPojo?>
        )
        fun onSuccessMore(
            id: MutableList<GetLeadsPojo?>
        )
        fun onFailureMore(msg: String)
        fun onFailure(msg: String)
        fun onLeadDetailsSuccess(body: ViewLead)
    }

    fun lead(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String,DateType: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadStatus"] = ""
        jsonParams["FromDate"] = FromDate
        jsonParams["ToDate"] = ToDate
        jsonParams["Status"] = "added"
        jsonParams["PageIndex"] = PageIndex
        jsonParams["PageSize"] = PageSize
        jsonParams["Keyword"] = Keyword
        jsonParams["DateType"] = DateType

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<GetLeadPojo> = ApiClient.getClient.getLeads(body)
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

        val call: Call<GetLeadPojo> = ApiClient.getClient.getLeads(body)
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

    fun save(userId: String, leadid: String, heading: String, remark: String, date: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadId"] = leadid
        jsonParams["Heading"] = heading
        jsonParams["Remark"] = remark
        jsonParams["RemindDate"] = date

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SendPojo> = ApiClient.getClient.savefollowup(body)
        call.enqueue(object : Callback<SendPojo> {
            override fun onFailure(call: Call<SendPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<SendPojo>,
                response: Response<SendPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onFailureMore(response.body()?.message!!)

                    } else {
                        listener.onFailureMore(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

    fun delete( followupid: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["FollowUpId"] = followupid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SendPojo> = ApiClient.getClient.deletefollowup(body)
        call.enqueue(object : Callback<SendPojo> {
            override fun onFailure(call: Call<SendPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<SendPojo>,
                response: Response<SendPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onFailureMore(response.body()?.message!!)

                    } else {
                        listener.onFailureMore(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

    fun leaddetails(userId: String, leadid: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadId"] = leadid

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<ViewLead> = ApiClient.getClient.getLeadDetails(body)
        call.enqueue(object : Callback<ViewLead> {
            override fun onFailure(call: Call<ViewLead>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<ViewLead>,
                response: Response<ViewLead>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onLeadDetailsSuccess(
                            response.body()!!
                        )
                    } else {
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }
}


