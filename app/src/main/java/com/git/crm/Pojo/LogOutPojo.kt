package com.git.crm.Pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LogOutPojo {
    @SerializedName("Status")
    @Expose
    val status: Boolean? = null
    @SerializedName("Message")
    @Expose
    val message: String? = null
}