package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class HeadingPojo {
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
     val data: List<HeadingList>? = null
}

class HeadingList {
    @SerializedName("Id")
    @Expose
     val id: Int? = null

    @SerializedName("Heading")
    @Expose
     val heading: String? = null
}
