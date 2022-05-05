package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SuSourcePojo {
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
     val data: List<SubSourceList>? = null
}

class SubSourceList {
    @SerializedName("SubSource_Id")
    @Expose
     val subSourceId: Int? = null

    @SerializedName("SubSource")
    @Expose
     val subSource: String? = null

    @SerializedName("Source_Id")
    @Expose
     val sourceId: Int? = null
}
