package com.git.crm.UserProfile

import com.git.crm.Pojo.UserProfilePojo


class UserProfilePresenter(var loginview: UserProfileView?, val loginInteractor: UserProfileInteractor) :
    UserProfileInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }



    override fun onSuccess(
        id: UserProfilePojo?
    ) {
        loginview?.apply {
            report(id)
            hideProgress()
        }
    }

    fun lead(userid: String) {
        loginview?.showProgress()
        loginInteractor.lead(userid,this)
    }


}