package com.git.crm.EditLeadOne

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditLeadOneInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<DistrictList>
        )
        fun onSourceSuccess(data: List<SourceList>)
        fun onSubSourceSuccess(data: List<SubSourceList>)
        fun onFailure(msg: String)
        fun onStateSuccess(data: MutableList<StateList?>)
        fun onLocationSuccess(data: List<LocationList>)
    }

    fun login(stateid: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["StateId"] = stateid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<DistrictPojo> = ApiClient.getClient.getDistrict(body)
        call.enqueue(object : Callback<DistrictPojo> {
            override fun onFailure(call: Call<DistrictPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<DistrictPojo>,
                response: Response<DistrictPojo>
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


    fun state( listener: OnLoginFinishedListener) {
        val call: Call<StatePojo> = ApiClient.getClient.getState()
        call.enqueue(object : Callback<StatePojo> {
            override fun onFailure(call: Call<StatePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<StatePojo>,
                response: Response<StatePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onStateSuccess(
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

    fun location(stateid: String,districtis: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["StateId"] = stateid
        jsonParams["DistrictId"] =districtis
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<LocationPojo> = ApiClient.getClient.getLocation(body)
        call.enqueue(object : Callback<LocationPojo> {
            override fun onFailure(call: Call<LocationPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<LocationPojo>,
                response: Response<LocationPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onLocationSuccess(
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

    fun source(listener: OnLoginFinishedListener) {
        val call: Call<SourcePojo> = ApiClient.getClient.getSource()
        call.enqueue(object : Callback<SourcePojo> {
            override fun onFailure(call: Call<SourcePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<SourcePojo>,
                response: Response<SourcePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSourceSuccess(
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

    fun subsource(sourceid: String, listener:OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["SourceId"] = sourceid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SuSourcePojo> = ApiClient.getClient.getSubSource(body)
        call.enqueue(object : Callback<SuSourcePojo> {
            override fun onFailure(call: Call<SuSourcePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<SuSourcePojo>,
                response: Response<SuSourcePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onSubSourceSuccess(
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


