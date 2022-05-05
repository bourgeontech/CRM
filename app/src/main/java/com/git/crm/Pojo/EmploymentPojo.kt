package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class EmploymentPojo {
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
     val data: List<EmploymentList>? = null
}

class EmploymentList {
    @SerializedName("Employment_id")
    @Expose
     val employmentId: Int? = null

    @SerializedName("Employment_Name")
    @Expose
     val employmentName: String? = null
}
