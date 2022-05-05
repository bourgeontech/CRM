package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class DistrictPojo {
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
     val data: List<DistrictList>? = null
}

class DistrictList {
    @SerializedName("District_id")
    @Expose
     val districtId: Int? = null

    @SerializedName("District")
    @Expose
     val district: String? = null
}
