package com.git.crm.UserProfile

import com.git.crm.Pojo.*


interface UserProfileView {

    fun showProgress()
    fun hideProgress()
    fun report(
        data: UserProfilePojo?
    )
    fun loginError(msg: String)

}