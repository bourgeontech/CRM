package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class UserProfilePojo {
    @SerializedName("Status")
    @Expose
     val status: Boolean? = null

    @SerializedName("Message")
    @Expose
     val message: String? = null

    @SerializedName("Count")
    @Expose
     val count: Int? = null

    @SerializedName("Data")
    @Expose
     val data: List<UserProfileDetails>? = null
}

class UserProfileDetails {
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

    @SerializedName("BranchID")
    @Expose
     val branchID: Any? = null

    @SerializedName("Branch")
    @Expose
     val branch: String? = null

    @SerializedName("User_Type")
    @Expose
     val userType: String? = null

}
