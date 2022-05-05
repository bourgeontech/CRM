package com.git.crm.EditLeadThree

import com.git.crm.Pojo.*


interface EditLeadThreeView {

    fun showProgress()
    fun hideProgress()
    fun Make(
        login: List<MakeList>
    )

    fun Make2(
        login: List<MakeList>,type:String
    )
    fun VariantSuccess(data: List<VariantList>)
    fun loginError(msg: String)
   fun  ModelSuccess(data: List<ModelList>)

    fun  ModelSuccessTwo(data: List<ModelList>,type:String)
   fun  YearSuccess(data: List<YearList>)
    fun IncomeSuccess(data: List<IncomeList>)
}