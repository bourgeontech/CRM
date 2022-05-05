package com.git.crm.Remarks

import com.git.crm.Pojo.*


interface RemarkView {

    fun showProgress()
    fun hideProgress()
    fun report(
        id: List<RemarkList>?
    )
    fun loginError(msg: String)
   fun  onsave(message: String)
}