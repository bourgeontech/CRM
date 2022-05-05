package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MakePojo {
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
     val data: List<MakeList>? = null
}

class MakeList {
    @SerializedName("make_id")
    @Expose
     val makeId: Int? = null

    @SerializedName("make")
    @Expose
     val make: String? = null

}
