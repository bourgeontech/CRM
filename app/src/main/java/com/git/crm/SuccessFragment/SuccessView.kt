package com.git.crm.SuccessFragment

import com.git.crm.Pojo.*


interface SuccessView {

    fun showProgress()
    fun hideProgress()
    fun loginError(msg: String)
    fun onFollowupAdd(msg:String)
    fun onHeadingSuccess(data: List<HeadingList>)
}