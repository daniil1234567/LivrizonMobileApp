package com.app.livrizon.security.token

import com.app.livrizon.model.type.ContactType
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.values.gson


class RestoreToken(jwt: String) : TokenBase(jwt) {
    val id: Int
    val username: String
    val registration: ContactType
    var confirm: Boolean = false

    init {
        val token = gson.fromJson(claims(), RestoreToken::class.java)
        this.type = TokenType.restore
        this.confirm = token.confirm
        this.id = token.id
        this.username = token.username
        this.registration = token.registration
        this.exp = token.exp
        this.iat = token.iat
    }
}