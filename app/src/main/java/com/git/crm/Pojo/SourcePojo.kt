package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SourcePojo {
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
     val data: List<SourceList>? = null
}

class SourceList {
    @SerializedName("Source_Id")
    @Expose
     val sourceId: Int? = null

    @SerializedName("Source")
    @Expose
     val source: String? = null
}
