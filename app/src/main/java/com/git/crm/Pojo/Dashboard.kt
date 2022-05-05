package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class Dashboard {

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
     val data: List<DashboardList>? = null
}

class DashboardList {
    @SerializedName("new_leads")
    @Expose
     val newLeads: Int? = null

    @SerializedName("intrested")
    @Expose
     val intrested: Int? = null

    @SerializedName("converted")
    @Expose
     val converted: Int? = null

    @SerializedName("new_followups")
    @Expose
     val newFollowups: Int? = null

    @SerializedName("not_intrested")
    @Expose
    val not_intrested: Int? = null

    @SerializedName("total_leads")
    @Expose
    val total_leads: Int? = null
}
