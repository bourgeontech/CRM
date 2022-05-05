package com.git.crm.Deals

import com.git.crm.Pojo.*


interface DealsView {

    fun showProgress()
    fun hideProgress()
    fun report(
        id: List<DealList>?
    )
    fun loginError(msg: String)
   fun  onsave(message: String)
}