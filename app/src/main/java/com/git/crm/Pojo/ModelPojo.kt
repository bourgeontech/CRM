package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ModelPojo {
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
     val data: List<ModelList>? = null
}

class ModelList {
    @SerializedName("model_id")
    @Expose
     val modelId: Int? = null

    @SerializedName("model")
    @Expose
     val model: String? = null
}
