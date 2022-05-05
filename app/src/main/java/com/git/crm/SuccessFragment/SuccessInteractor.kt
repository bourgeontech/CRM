package com.git.crm.SuccessFragment

import androidx.collection.ArrayMap
import com.git.crm.Followuptask.PollowUpTaskInteractor
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SuccessInteractor {

    interface OnLoginFinishedListener {
        fun onFailure(msg: String)
        fun onAdd(msg: String)
        fun onHeading(data: List<HeadingList>)
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
                        listener.onAdd(response.body()?.message!!)

                    } else {
                        listener.onAdd(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

    fun heading(listener: OnLoginFinishedListener) {

        val call: Call<HeadingPojo> = ApiClient.getClient.get_followup_heading()
        call.enqueue(object : Callback<HeadingPojo> {
            override fun onFailure(call: Call<HeadingPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<HeadingPojo>,
                response: Response<HeadingPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onHeading(response.body()?.data!!)

                    } else {
                        listener.onAdd(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

}


