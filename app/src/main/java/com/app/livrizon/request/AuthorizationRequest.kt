package com.app.livrizon.request

import com.app.livrizon.model.authorization.*
import com.app.livrizon.model.edit.profile.save.AccountInformationSave
import com.app.livrizon.model.edit.profile.save.AccountSave
import com.app.livrizon.model.profile.Account
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.response.Response
import com.app.livrizon.services.AuthorizationService
import com.app.livrizon.values.*
import io.ktor.client.request.*

object AuthorizationRequest : AuthorizationService {
    override suspend fun account(): Profile {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.account)
            headers.append(Parameters.auth, token.jwt)
        }, Profile::class.java)
    }

    override suspend fun information(save: AccountInformationSave): Jwt {
        return httpClient.post {
            url(HttpRoutes.information)
            headers.append(Parameters.auth, token.jwt)
            body = gson.toJson(save)
        }
    }

    override suspend fun login(login: Login): Jwt {
        return httpClient.post {
            url(HttpRoutes.login)
            body = gson.toJson(login)
        }
    }

    override suspend fun authentication(authentication: Authentication): Jwt {
        return httpClient.get {
            url(HttpRoutes.authentication)
            body = gson.toJson(authentication)
        }
    }

    override suspend fun confirm(code: Int): Jwt {
        return httpClient.get {
            url(HttpRoutes.confirm)
            headers.append(Parameters.auth, token.jwt)
            parameter(Parameters.code, code)
        }
    }

    override suspend fun registration(save: AccountSave): Jwt {
        return httpClient.post {
            url(HttpRoutes.registration)
            headers.append(Parameters.auth, token.jwt)
            body = gson.toJson(save)
        }
    }

    override suspend fun getMyAccounts(accounts: Array<Authentication>): Array<Account> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.accounts)
            body = gson.toJson(accounts)
        }, Array<Account>::class.java)
    }

    override suspend fun confirmName(confirm: ConfirmName): Jwt {
        return httpClient.get {
            url(HttpRoutes.confirm_name)
            body = gson.toJson(confirm)
        }
    }

    override suspend fun getMyAccount(): Profile {
        return httpClient.get {
            url(HttpRoutes.registration)
            headers.append(Parameters.auth, token.jwt)
        }
    }

    override suspend fun updatePassword(password: String): Jwt {
        return httpClient.put {
            url(HttpRoutes.password)
            headers.append(Parameters.auth, token.jwt)
            parameter(Parameters.password, password)
        }
    }

    override suspend fun confirmAccount(): Response {
        return httpClient.get {
            url(HttpRoutes.confirm_account)
            headers.append(Parameters.auth, token.jwt)
        }
    }
}