package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class IncomePojo {
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
     val data: List<IncomeList>? = null
}

class IncomeList {

    @SerializedName("Id")
    @Expose
     val id: Int? = null

    @SerializedName("Value")
    @Expose
     val value: String? = null
}
