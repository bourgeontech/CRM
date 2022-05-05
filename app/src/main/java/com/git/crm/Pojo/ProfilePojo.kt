package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ProfilePojo {

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
     val data: List<ProfileList>? = null
}

class ProfileList {

    @SerializedName("Sales_id")
    @Expose
     val salesId: Int? = null

    @SerializedName("Customer_Name")
    @Expose
     val customerName: String? = null

    @SerializedName("Customer_LastName")
    @Expose
     val customerLastName: String? = null

    @SerializedName("Customer_MobNo")
    @Expose
     val customerMobNo: String? = null

    @SerializedName("Customer_EmailId")
    @Expose
     val customerEmailId: String? = null

    @SerializedName("FollowUp")
    @Expose
     val followUp: String? = null

    @SerializedName("FollowUp_Date")
    @Expose
     val followUpDate: String? = null

    @SerializedName("Remark")
    @Expose
     val remark: String? = null

    @SerializedName("Status")
    @Expose
     val status: String? = null

    @SerializedName("Status_Date")
    @Expose
     val statusDate: String? = null

    @SerializedName("Deal")
    @Expose
     val deal: String? = null

    @SerializedName("Deal_Date")
    @Expose
     val dealDate: String? = null

    @SerializedName("CreatedUserId")
    @Expose
     val createdUserId: Int? = null

    @SerializedName("CreatedUserName")
    @Expose
     val createdUserName: String? = null
}
