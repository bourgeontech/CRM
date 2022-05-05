package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class StatePojo {
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
    lateinit var data: MutableList<StateList?>
}

class StateList {
    @SerializedName("State_id")
    @Expose
    var stateId: Int? = null

    @SerializedName("State")
    @Expose
    var state: String? = null
}
