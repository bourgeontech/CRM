package com.git.crm.CustomerDetailsTwo

import com.git.crm.Pojo.*


interface CustomerPageTwoView {

    fun showProgress()
    fun hideProgress()
    fun Plan(
        plan: List<PlanToBuyList>
    )
    fun EmploymentSuccess(data: List<EmploymentList>)
    fun loginError(msg: String)
    fun IncomeSuccess(data: List<IncomeList>)
}