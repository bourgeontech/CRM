package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SendPojoTwo {
    @SerializedName("Status")
    @Expose
     val status: Boolean? = null
    @SerializedName("Message")
    @Expose
     val message: String? = null

    @SerializedName("LeadId")
    @Expose
    val LeadId: String? = null


    @SerializedName("Count")
    @Expose
     val count: Int? = null

    @SerializedName("Data")
    @Expose
     val data: List<LeadDetails>? = null
}

class LeadDetails {
    @SerializedName("Sales_id")
    @Expose
     val salesId: Int? = null

    @SerializedName("status")
    @Expose
     val status: Int? = null

}
