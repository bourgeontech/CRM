package com.git.crm.FollowUpLeads

import com.git.crm.Pojo.GetLeadsPojo


class FollowUpLeadPresenter(var loginview: FollowUpLeadView?, val loginInteractor: PollowUpLeadInteractor) :
    PollowUpLeadInteractor.OnLoginFinishedListener {
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