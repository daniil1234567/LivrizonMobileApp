package com.app.livrizon.services

import com.app.livrizon.model.authorization.Authentication

interface SqlRequestImpl {
    fun saveAccount(account: Authentication)
    fun getMyAccounts():MutableList<Authentication>?
    fun getAccount(username:String): Authentication
}