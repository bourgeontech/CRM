package com.git.crm.UserProfile

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserProfileInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: UserProfilePojo?
        )

        fun onFailure(msg: String)

    }

    fun lead(userid: String,  listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userid

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<UserProfilePojo> = ApiClient.getClient.getUserProfile(body)
        call.enqueue(object : Callback<UserProfilePojo> {
            override fun onFailure(call: Call<UserProfilePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<UserProfilePojo>,
                response: Response<UserProfilePojo>
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


