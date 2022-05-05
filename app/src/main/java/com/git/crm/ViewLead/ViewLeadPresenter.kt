package com.git.crm.ViewLead

import com.git.crm.Pojo.ViewLead


class ViewLeadPresenter(var loginview: ViewLeadView?, val loginInteractor: VieLeadInteractor) :
    VieLeadInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }



    override fun onSuccess(
        id: ViewLead?
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun lead(userid: String,leadid:String) {
        loginview?.showProgress()
        loginInteractor.lead(userid,leadid, this)
    }


}