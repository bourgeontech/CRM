package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LeadPojo {
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
     val data: List<LeadList>? = null
}

class LeadList {
    @SerializedName("LeadStatus_Id")
    @Expose
     val leadStatusId: Int? = null

    @SerializedName("LeadStatus")
    @Expose
     val leadStatus: String? = null
}
