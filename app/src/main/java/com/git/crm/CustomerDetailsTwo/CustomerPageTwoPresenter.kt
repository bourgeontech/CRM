package com.git.crm.CustomerDetailsTwo

import com.git.crm.Pojo.*


class CustomerPageTwoPresenter(var loginview: CustomerPageTwoView?, val loginInteractor: CustomerPageTwoInteractor) :
    CustomerPageTwoInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }

    override fun onEmplomentSuccess(data: List<EmploymentList>) {
        loginview?.apply {
            EmploymentSuccess(data)
            hideProgress()
        }
    }

    override fun onIncomeSuccess(data: List<IncomeList>) {
        loginview?.apply {
            IncomeSuccess(data)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<PlanToBuyList>
    ) {
        loginview?.apply {
            Plan(id)
            hideProgress()
        }
    }

    fun getPlan() {
        loginview?.showProgress()
        loginInteractor.plan( this)
    }

    fun getEmployment() {
        loginview?.showProgress()
        loginInteractor.employment( this)
    }

    fun getIncome() {
        loginview?.showProgress()
        loginInteractor.income( this)
    }

}