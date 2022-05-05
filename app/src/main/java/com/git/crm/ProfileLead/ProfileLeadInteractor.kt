package com.git.crm.ProfileLead

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileLeadInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<ProfileList>
        )

        fun onFailure(msg: String)
    }

    fun login(userid: String,LeadId: String,   listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userid
        jsonParams["LeadId"] = LeadId

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<ProfilePojo> = ApiClient.getClient.getProfile(body)
        call.enqueue(object : Callback<ProfilePojo> {
            override fun onFailure(call: Call<ProfilePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<ProfilePojo>,
                response: Response<ProfilePojo>
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


