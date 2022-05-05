package com.git.crm.Login

import com.git.crm.Pojo.LoginDetails


class LoginPresenter(var loginview: LoginView?, val loginInteractor: LoginInteractor) :
    LoginInteractor.OnLoginFinishedListener {

    override fun onFailure(msg: String) {
        loginview?.apply {
            loginError(msg)
            hideProgress()
        }
    }


    override fun onSuccess(
        id: List<LoginDetails>?
    ) {
        loginview?.apply {
            navigateToHome(id)
            hideProgress()
        }
    }

    fun validateCredentials(mobile: String, password: String, deviceId: String?) {
        loginview?.showProgress()
        loginInteractor.login(mobile, password,deviceId, this)
    }

    fun onDestroy() {
        loginview = null
    }
}