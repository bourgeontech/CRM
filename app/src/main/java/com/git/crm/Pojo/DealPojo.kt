package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DealPojo {
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
     val data: List<DealList>? = null
}

class DealList {
    @SerializedName("Deal_Id")
    @Expose
     val dealId: Int? = null

    @SerializedName("Deal")
    @Expose
     val deal: String? = null

    @SerializedName("Created_Date")
    @Expose
     val createdDate: String? = null
}
