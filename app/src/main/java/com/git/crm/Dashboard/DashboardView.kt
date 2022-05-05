package com.git.crm.Dashboard

import com.git.crm.Pojo.DashboardList
import com.git.crm.Pojo.LoginDetails


interface DashboardView {

    fun showProgress()
    fun hideProgress()
    fun navigateToHome(
        login: List<DashboardList>?
    )
    fun loginError(msg: String)
}