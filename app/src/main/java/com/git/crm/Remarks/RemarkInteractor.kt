package com.git.crm.Remarks

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RemarkInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<RemarkList>
        )

        fun onFailure(msg: String)
        fun onsave(message: String)
    }

    fun remark(userid: String,status: String,   listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userid
        jsonParams["LeadId"] = status

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<RemarkPojo> = ApiClient.getClient.getRemark(body)
        call.enqueue(object : Callback<RemarkPojo> {
            override fun onFailure(call: Call<RemarkPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<RemarkPojo>,
                response: Response<RemarkPojo>
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

    fun saveremark(leadid: String, userId: String, statusid: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId
        jsonParams["LeadId"] = leadid
        jsonParams["Remark"] = statusid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SendPojo> = ApiClient.getClient.saveREmark(body)
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
                        listener.onsave(response.body()?.message!!)
                    } else {
                        listener.onsave(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }
}


