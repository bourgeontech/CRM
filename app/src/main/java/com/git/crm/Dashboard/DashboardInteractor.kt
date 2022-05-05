package com.git.crm.Dashboard

import androidx.collection.ArrayMap
import com.git.crm.Pojo.Dashboard
import com.git.crm.Pojo.DashboardList
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DashboardInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<DashboardList>?
        )

        fun onFailure(msg: String)
    }

    fun dashboard(id: String, name: String, listener: OnLoginFinishedListener) {
        println("working.......1")
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = id
        jsonParams["DateType"] = name
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())
        val call: Call<Dashboard> = ApiClient.getClient.getDashboard(body)
        call.enqueue(object : Callback<Dashboard> {
            override fun onFailure(call: Call<Dashboard>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
                println("working.......2")
            }

            override fun onResponse(
                call: Call<Dashboard>,
                response: Response<Dashboard>
            ) {

                if (response.isSuccessful) {
                    println("working.......3")
                    if (response.body()?.status!!) {
                        println("working.......4")
                        listener.onSuccess(
                            response.body()?.data!!)
                    } else {
                        println("working.......5")
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    println("working.......6")
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }
}


