package com.git.crm.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LogiPojo {
    @SerializedName("Status")
    @Expose
     val status: Boolean? = null

    @SerializedName("Message")
    @Expose
     val message: String? = null

    @SerializedName("Data")
    @Expose
     val data: List<LoginDetails>? = null
}

class LoginDetails {

    @SerializedName("User_Code")
    @Expose
     val userCode: Int? = null

    @SerializedName("First_Name")
    @Expose
     val firstName: String? = null

    @SerializedName("Last_Name")
    @Expose
     val lastName: String? = null

    @SerializedName("mobile_number")
    @Expose
     val mobileNumber: String? = null

    @SerializedName("E_mail")
    @Expose
     val eMail: String? = null

    @SerializedName("status")
    @Expose
     val status: Int? = null

    @SerializedName("BranchID")
    @Expose
    val BranchID: Int? = null

    @SerializedName("Branch")
    @Expose
    val Branch: String? = null

    @SerializedName("User_Type")
    @Expose
    val User_Type: String? = null
}
