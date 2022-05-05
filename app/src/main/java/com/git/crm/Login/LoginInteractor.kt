package com.git.crm.Login

import androidx.collection.ArrayMap
import com.git.crm.Pojo.LogiPojo
import com.git.crm.Pojo.LoginDetails
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<LoginDetails>?
        )

        fun onFailure(msg: String)
    }

    fun login(mobile: String, password: String,deviceId: String?, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserName"] = mobile
        jsonParams["Password"] = password
        jsonParams["DeviceToken"] = deviceId
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<LogiPojo> = ApiClient.getClient.userLogin(body)
        call.enqueue(object : Callback<LogiPojo> {
            override fun onFailure(call: Call<LogiPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<LogiPojo>,
                response: Response<LogiPojo>
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


