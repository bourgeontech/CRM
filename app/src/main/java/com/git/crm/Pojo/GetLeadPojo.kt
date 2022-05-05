package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class GetLeadPojo {

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
    lateinit var data: MutableList<GetLeadsPojo?>
}

class GetLeadsPojo {
    @SerializedName("Sales_id")
    @Expose
     val salesId: Int? = null

    @SerializedName("Sales_Lead_Code")
    @Expose
     val salesLeadCode: String? = null

    @SerializedName("Sales_Lead_Date")
    @Expose
     val salesLeadDate: String? = null

    @SerializedName("Customer_Name")
    @Expose
     val customerName: String? = null

    @SerializedName("Customer_MobNo")
    @Expose
     val customerMobNo: String? = null

    @SerializedName("State_id")
    @Expose
     val stateId: Int? = null

    @SerializedName("District_id")
    @Expose
     val districtId: Int? = null

    @SerializedName("Lead_LoginID")
    @Expose
     val leadLoginID: Int? = null

    @SerializedName("created_date")
    @Expose
     val createdDate: String? = null

    @SerializedName("Lead_Status_Date")
    @Expose
     val leadStatusDate: Any? = null

    @SerializedName("Branch_id")
    @Expose
     val branchId: Int? = null

    @SerializedName("Lead_Assigned_to")
    @Expose
     val leadAssignedTo: Int? = null

    @SerializedName("Remark")
    @Expose
     val remark: String? = null

    @SerializedName("Customer_LastName")
    @Expose
    val Customer_LastName: String? = null

    @SerializedName("Lead_status")
    @Expose
    val Lead_status: String? = null

    @SerializedName("addedby")
    @Expose
    val addedby: String? = null
}
