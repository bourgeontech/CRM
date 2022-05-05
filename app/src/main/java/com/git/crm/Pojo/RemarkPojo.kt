package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class RemarkPojo {
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
     val data: List<RemarkList>? = null
}

class RemarkList {
    @SerializedName("Remark_Id")
    @Expose
     val remarkId: Int? = null

    @SerializedName("Remark")
    @Expose
     val remark: String? = null

    @SerializedName("Created_Date")
    @Expose
     val createdDate: String? = null
}
