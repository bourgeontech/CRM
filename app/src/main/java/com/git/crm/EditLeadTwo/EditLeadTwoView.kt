package com.git.crm.EditLeadTwo

import com.git.crm.Pojo.*


interface EditLeadTwoView {

    fun showProgress()
    fun hideProgress()
    fun Plan(
        plan: List<PlanToBuyList>
    )
    fun EmploymentSuccess(data: List<EmploymentList>)
    fun loginError(msg: String)
    fun IncomeSuccess(data: List<IncomeList>)

}