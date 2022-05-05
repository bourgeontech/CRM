package com.git.crm.Converted

import com.git.crm.Pojo.GetLeadsPojo


class ConvertedLeadPresenter(var loginview: ConvertedLeadView?, val loginInteractor: ConvertedLeadInteractor) :
    ConvertedLeadInteractor.OnLoginFinishedListener {
    override fun onSuccess( id: MutableList<GetLeadsPojo?>) {
        loginview?.apply {
            Lead(id)
            hideProgress()
        }
    }

    override fun onSuccessMore( id: MutableList<GetLeadsPojo?>) {
        loginview?.apply {
            LeadMore(id)
            hideProgress()
        }
    }

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }
    override fun onFailureMore(msg: String) {
        loginview?.apply {
//            loginError(msg)
              hideProgress()
        }
    }

    fun lead(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String) {
        loginview?.showProgress()
        loginInteractor.lead(userId, LeadStatus,FromDate,ToDate,Status,PageIndex,PageSize,Keyword, this)
    }

    fun leadmore(userId: String, LeadStatus: String,FromDate: String,ToDate: String,Status: String,PageIndex: String,PageSize: String,Keyword: String) {
        loginview?.showProgress()
        loginInteractor.leadmore(userId, LeadStatus,FromDate,ToDate,Status,PageIndex,PageSize,Keyword, this)
    }


}