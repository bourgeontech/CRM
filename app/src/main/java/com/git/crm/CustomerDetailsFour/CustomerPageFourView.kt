package com.git.crm.CustomerDetailsFour

import com.git.crm.Pojo.*


interface CustomerPageFourView {

    fun showProgress()
    fun hideProgress()
    fun Make(
        login: List<LeadList>
    )

    fun Make2(
        login: List<MakeList>,type:String
    )
    fun VariantSuccess(data: List<UserList>)
    fun loginError(msg: String)
   fun  ModelSuccess(data: List<BranchList>)
    fun sendSucces(message:String,leadid: String)

    fun  ModelSuccessTwo(data: List<ModelList>,type:String)
}