package com.git.crm.Pojo

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class BranchPojo {
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
     val data: MutableList<BranchList>? = null
}

class BranchList {
    @SerializedName("branch_id")
    @Expose
     val branchId: Int? = null

    @SerializedName("branch")
    @Expose
     val branch: String? = null

}
