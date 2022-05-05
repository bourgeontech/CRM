package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class LocationPojo {
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
     val data: List<LocationList>? = null
}

class LocationList {
    @SerializedName("Location_id")
    @Expose
     val locationId: Int? = null

    @SerializedName("Location")
    @Expose
     val location: String? = null
}
