package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class SalesReportPojo {
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
     val data: List<SalesReportList>? = null
}

class SalesReportList {
    @SerializedName("MakeId")
    @Expose
     val makeId: String? = null

    @SerializedName("Make")
    @Expose
     val make: String? = null

    @SerializedName("TotalSale")
    @Expose
     val totalSale: Int? = null
}
