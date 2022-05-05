package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class PlanToBuyPojo {
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
     val data: List<PlanToBuyList>? = null
}

class PlanToBuyList {
    @SerializedName("planto_buyid")
    @Expose
     val plantoBuyid: Int? = null

    @SerializedName("plans")
    @Expose
     val plans: String? = null
}
