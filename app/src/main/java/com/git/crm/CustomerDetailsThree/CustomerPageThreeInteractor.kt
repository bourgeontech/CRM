package com.git.crm.CustomerDetailsThree

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerPageThreeInteractor {

    interface OnLoginFinishedListener {
        fun onMakeSuccess(
            id: List<MakeList>
        )
        fun onMakeSuccess2(
            id: List<MakeList>,type:String
        )

        fun onFailure(msg: String)
        fun onVariantSuccess(data: List<VariantList>)
        fun onModelSuccess(data: List<ModelList>)
        fun onModelSuccessTwo(data: List<ModelList>,type:String)
        fun onYearSuccess(data: List<YearList>)
        fun onIncomeSuccess(data: List<IncomeList>)
    }

    fun make(listener: OnLoginFinishedListener) {

        val call: Call<MakePojo> = ApiClient.getClient.getMake()
        call.enqueue(object : Callback<MakePojo> {
            override fun onFailure(call: Call<MakePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<MakePojo>,
                response: Response<MakePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onMakeSuccess(
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


    fun make2(type:String,listener: OnLoginFinishedListener) {

        val call: Call<MakePojo> = ApiClient.getClient.getMake()
        call.enqueue(object : Callback<MakePojo> {
            override fun onFailure(call: Call<MakePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<MakePojo>,
                response: Response<MakePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onMakeSuccess2(
                            response.body()?.data!!,type)
                    } else {
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }



    fun variant(modelid:String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["ModelId"] = modelid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<VariantPojo> = ApiClient.getClient.getVariant(body)
        call.enqueue(object : Callback<VariantPojo> {
            override fun onFailure(call: Call<VariantPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<VariantPojo>,
                response: Response<VariantPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onVariantSuccess(
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

    fun model( makeid:String,listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["MakeId"] = makeid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<ModelPojo> = ApiClient.getClient.getModel(body)
        call.enqueue(object : Callback<ModelPojo> {
            override fun onFailure(call: Call<ModelPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<ModelPojo>,
                response: Response<ModelPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onModelSuccess(
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

    fun model2(type:String,makeid:String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["MakeId"] = makeid
        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<ModelPojo> = ApiClient.getClient.getModel(body)
        call.enqueue(object : Callback<ModelPojo> {
            override fun onFailure(call: Call<ModelPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<ModelPojo>,
                response: Response<ModelPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onModelSuccessTwo(
                            response.body()?.data!!,type)
                    } else {
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    listener.onFailure("There is some problem.Try again")
                }

            }
        })
    }

    fun year( listener: OnLoginFinishedListener) {
        val call: Call<YearPojo> = ApiClient.getClient.getYear()
        call.enqueue(object : Callback<YearPojo> {
            override fun onFailure(call: Call<YearPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<YearPojo>,
                response: Response<YearPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onYearSuccess(
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

    fun budjet(listener: OnLoginFinishedListener) {
        val call: Call<IncomePojo> = ApiClient.getClient.get_budget_range()
        call.enqueue(object : Callback<IncomePojo> {
            override fun onFailure(call: Call<IncomePojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<IncomePojo>,
                response: Response<IncomePojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onIncomeSuccess(
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


