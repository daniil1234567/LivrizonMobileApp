package com.app.livrizon.security.token

import com.app.livrizon.model.type.ContactType
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.request.gson

class TemporaryToken(jwt: String) : TokenBase(jwt) {
    val username: String
    val registration: ContactType

    init {
        val token = gson.fromJson(claims(), TemporaryToken::class.java)
        this.type = TokenType.temporary
        this.username = token.username
        this.registration = token.registration
        this.exp = token.exp
        this.iat = token.iat
    }
}