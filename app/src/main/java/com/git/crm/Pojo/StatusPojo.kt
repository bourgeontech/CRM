package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StatusPojo {

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
     val data: List<StatusList>? = null
}

class StatusList {
    @SerializedName("Status_Id")
    @Expose
     val statusId: Int? = null

    @SerializedName("LeadStatus")
    @Expose
     val leadStatus: String? = null

    @SerializedName("Created_Date")
    @Expose
     val createdDate: String? = null
    @SerializedName("Remark")
    @Expose
    val Remark: String? = null
}
