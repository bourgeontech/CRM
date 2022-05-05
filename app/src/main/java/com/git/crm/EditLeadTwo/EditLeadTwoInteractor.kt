package com.git.crm.EditLeadTwo

import androidx.collection.ArrayMap
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditLeadTwoInteractor {

    interface OnLoginFinishedListener {
        fun onSuccess(
            id: List<PlanToBuyList>
        )

        fun onFailure(msg: String)
        fun onEmplomentSuccess(data: List<EmploymentList>)
        fun onIncomeSuccess(data: List<IncomeList>)

    }

    fun plan(listener: OnLoginFinishedListener) {
        val call: Call<PlanToBuyPojo> = ApiClient.getClient.getPlan()
        call.enqueue(object : Callback<PlanToBuyPojo> {
            override fun onFailure(call: Call<PlanToBuyPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<PlanToBuyPojo>,
                response: Response<PlanToBuyPojo>
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


    fun employment( listener: OnLoginFinishedListener) {
        val call: Call<EmploymentPojo> = ApiClient.getClient.getEmployment()
        call.enqueue(object : Callback<EmploymentPojo> {
            override fun onFailure(call: Call<EmploymentPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<EmploymentPojo>,
                response: Response<EmploymentPojo>
            ) {

                if (response.isSuccessful) {
                    if (response.body()?.status!!) {
                        listener.onEmplomentSuccess(
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

    fun income(listener: OnLoginFinishedListener) {
        val call: Call<IncomePojo> = ApiClient.getClient.get_income_range()
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


