package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class FollowUpPojo {
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
     val data: List<FollowUpList>? = null
}

class FollowUpList {
    @SerializedName("FollowUp_Id")
    @Expose
     val followUpId: Int? = null

    @SerializedName("Heading")
    @Expose
     val heading: String? = null

    @SerializedName("Remark")
    @Expose
     val remark: String? = null

    @SerializedName("Remind_Date")
    @Expose
     val remindDate: String? = null

    @SerializedName("Remind_Time")
    @Expose
     val remindTime: String? = null

    @SerializedName("Remind_DateTime")
    @Expose
     val remindDateTime: String? = null

    @SerializedName("Created_Date")
    @Expose
    val Created_Date: String? = null

    @SerializedName("Lead_Status")
    @Expose
    val Lead_Status: String? = null
}
