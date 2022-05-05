package com.git.crm.Login

import com.git.crm.Pojo.LoginDetails


interface LoginView {

    fun showProgress()
    fun hideProgress()
    fun navigateToHome(
        login: List<LoginDetails>?
    )
    fun loginError(msg: String)
}