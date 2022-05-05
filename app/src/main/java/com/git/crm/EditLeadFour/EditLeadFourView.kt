package com.git.crm.EditLeadFour

import com.git.crm.Pojo.*


interface EditLeadFourView {

    fun showProgress()
    fun hideProgress()
    fun Make(
        login: List<LeadList>
    )

    fun Make2(
        login: List<MakeList>,
        type: String
    )
    fun VariantSuccess(data: List<UserList>)
    fun loginError(msg: String)
   fun  ModelSuccess(data: List<BranchList>)
    fun sendSucces(message:String)

    fun  ModelSuccessTwo(data: List<ModelList>, type: String)

    fun sourceSucces(data: List<SourceList>)
    fun  subsourceSucces(data: List<SubSourceList>)
}