package com.app.livrizon.services

import com.app.livrizon.model.authorization.Authentication
import com.app.livrizon.model.authorization.ConfirmName
import com.app.livrizon.model.authorization.Jwt
import com.app.livrizon.model.authorization.Login
import com.app.livrizon.model.edit.profile.save.AccountInformationSave
import com.app.livrizon.model.edit.profile.save.AccountSave
import com.app.livrizon.model.profile.Account
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.response.Response

interface AuthorizationRequestImpl {
    suspend fun account(): Profile
    suspend fun information(save: AccountInformationSave): Jwt
    suspend fun login(login: Login): Jwt
    suspend fun authentication(authentication: Authentication): Jwt
    suspend fun confirm(code: Int): Jwt
    suspend fun registration(save: AccountSave): Jwt
    suspend fun getMyAccounts(accounts: Array<Authentication>): Array<Account>
    suspend fun confirmName(confirm: ConfirmName): Jwt
    suspend fun getMyAccount(): Profile
    suspend fun updatePassword(password: String): Jwt
    suspend fun confirmAccount(): Response
}