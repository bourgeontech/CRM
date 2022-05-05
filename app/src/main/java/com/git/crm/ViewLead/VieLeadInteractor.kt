package com.git.crm.ViewLead

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class VieLeadInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: ViewLead?
        )

        fun onFailure(msg: String)

    }

    fun lead(userid: String,leadid: String,   listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userid
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
                        listener.onSuccess(
                            response.body())
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


