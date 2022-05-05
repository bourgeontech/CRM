package com.git.crm.ProfileLead

import com.git.crm.Pojo.ProfileList


class ProfileLeadPresenter(var loginview: ProfileLeadView?, val loginInteractor: ProfileLeadInteractor) :
    ProfileLeadInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<ProfileList>
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun report(userid: String,LeadId:String) {
        loginview?.showProgress()
        loginInteractor.login(userid,LeadId, this)
    }


}