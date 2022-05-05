package com.git.crm.EditLeadFour

import android.widget.Toast
import androidx.collection.ArrayMap
import androidx.fragment.app.FragmentActivity
import com.git.crm.CustomerDetailsFour.CustomerPageFourInteractor
import com.git.crm.MainActivity
import com.git.crm.Pojo.*
import com.study.firebasecrash.Retrofit.ApiClient
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditLeadFourInteractor {

    interface OnLoginFinishedListener {
        fun onMakeSuccess(
            id: List<LeadList>
        )
        fun onMakeSuccess2(
            id: List<MakeList>,type:String
        )

        fun onFailure(msg: String)
        fun onVariantSuccess(data: List<UserList>)
        fun onModelSuccess(data: List<BranchList>)
        fun onModelSuccessTwo(data: List<ModelList>,type:String)
        fun onSendSuccess(message: String)
        fun onSourceSuccess(data: List<SourceList>)
        fun onSubSourceSuccess(data: List<SubSourceList>)
    }

    fun make(listener: OnLoginFinishedListener) {

        val call: Call<LeadPojo> = ApiClient.getClient.getLead()
        call.enqueue(object : Callback<LeadPojo> {
            override fun onFailure(call: Call<LeadPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<LeadPojo>,
                response: Response<LeadPojo>
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



    fun variant(userId: String, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["UserCode"] = userId

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<UserPojo> = ApiClient.getClient.getUser(body)
        call.enqueue(object : Callback<UserPojo> {
            override fun onFailure(call: Call<UserPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<UserPojo>,
                response: Response<UserPojo>
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

    fun model( listener: OnLoginFinishedListener) {

        val call: Call<BranchPojo> = ApiClient.getClient.getBranch()
        call.enqueue(object : Callback<BranchPojo> {
            override fun onFailure(call: Call<BranchPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
            }

            override fun onResponse(
                call: Call<BranchPojo>,
                response: Response<BranchPojo>
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

    fun model2( type:String,makeid:String,listener: OnLoginFinishedListener) {
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


    fun send(userId:String, name:String, email:String,
             phone:String,
             pincode:String,
             state:String,
             district:String,
             location:String,
             employment:String,
             income:String,
             budjet:String,
             leaddate:String,
             leadloginid:String,
             arrayone:JSONArray,
             array:JSONArray, array3: JSONArray, branchid:String, leadstatusid:String, leadassignedid:String, remark:String, plantobuyid: String?, lastname:String?, id: String?, sourceid: String?, subsourceid: String?, vehiclenumber: String?, activity: FragmentActivity?, listener: OnLoginFinishedListener) {
        val jsonParams: MutableMap<String?, Any?> = ArrayMap()
        jsonParams["Id"] = id
        jsonParams["UserCode"] = userId
        jsonParams["Name"] = name
        jsonParams["LastName"] = lastname
        jsonParams["Contact"] = phone
        jsonParams["EmailId"] = email
        jsonParams["Pincode"] = pincode
        jsonParams["LocationId"] = location
        jsonParams["StateId"] = state
        jsonParams["DistrictId"] = district
        jsonParams["LeadStatusId"] =leadstatusid
        jsonParams["BranchId"] = branchid
        jsonParams["AssignedtoId"] = leadassignedid

        jsonParams["Remark"] =remark
        jsonParams["CurrentVehicle"] = arrayone
        jsonParams["PrefferedVehicle"] = array
        jsonParams["SuggestedVehicle"] = array3

        jsonParams["PlantoBuyId"] = plantobuyid
        jsonParams["LeadDate"] = leaddate
        jsonParams["Budget"] = budjet
        jsonParams["EmploymentId"] = employment
        jsonParams["AnualIncome"] = income
        jsonParams["SourceId"] = sourceid
        jsonParams["SubSourceId"] = subsourceid
        jsonParams["TestDriveNo"] = vehiclenumber


        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSONObject(jsonParams).toString())

        val call: Call<SendPojo> = ApiClient.getClient.sendDate(body)
        call.enqueue(object : Callback<SendPojo> {
            override fun onFailure(call: Call<SendPojo>, t: Throwable) {
                listener.onFailure("There is some problem.Try again")
                println("working.....5.....kkkk")
            }

            override fun onResponse(
                call: Call<SendPojo>,
                response: Response<SendPojo>
            ) {

                if (response.isSuccessful) {
                    println("working.....1.....kkkk")
                    if (response.body()?.status!!) {
                        println("working.....3.....kkkk")
                        listener.onSendSuccess(response.body()?.message!!)
                    } else {
                        println("working.....4.....kkkk")
                        listener.onFailure(response.body()?.message!!)
                    }
                } else {
                    println("working.....2.....kkkk")
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

    fun subsource(sourceid: String, listener: OnLoginFinishedListener) {
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


