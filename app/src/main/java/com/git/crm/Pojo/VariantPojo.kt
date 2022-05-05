package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class VariantPojo {
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
     val data: List<VariantList>? = null
}

class VariantList {
    @SerializedName("variant_id")
    @Expose
     val variantId: Int? = null

    @SerializedName("variant")
    @Expose
     val variant: String? = null
}
