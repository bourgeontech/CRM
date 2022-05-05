package com.git.crm.Report

import androidx.collection.ArrayMap
import com.git.crm.Pojo.LogiPojo
import com.git.crm.Pojo.LoginDetails
import com.git.crm.Pojo.SalesReportList
import com.git.crm.Pojo.SalesReportPojo
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ReportInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<SalesReportList>?
        )

        fun onFailure(msg: String)
    }

    fun login(userid: String,  listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SalesReportPojo> = ApiClient.getClient.getReport(body)
        call.enqueue(object : Callback<SalesReportPojo> {
            override fun onFailure(call: Call<SalesReportPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<SalesReportPojo>,
                response: Response<SalesReportPojo>
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
}


