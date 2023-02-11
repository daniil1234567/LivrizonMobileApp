package com.app.livrizon.security.token

import com.app.livrizon.model.type.GenderType
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.security.Role
import com.app.livrizon.request.gson


class AccessToken(jwt: String) : TokenBase(jwt) {
    val id: Int
    val admin: Boolean
    val confirm: Boolean
    val role: Role
    val name: String
    val avatar: String
    val city_id: Int
    val phrase_id: Int
    val age: Int
    val gender: GenderType
    val recruiter: Int
    val detail_id: Int

    init {
        val token = gson.fromJson(claims(), AccessToken::class.java)
        this.type = TokenType.access
        this.id = token.id
        this.admin = token.admin
        this.confirm = token.confirm
        this.role = token.role
        this.name = token.name
        this.avatar = token.avatar
        this.city_id = token.city_id
        this.phrase_id = token.phrase_id
        this.age = token.age
        this.gender = token.gender
        this.recruiter = token.recruiter
        this.detail_id = token.detail_id
        this.exp = token.exp
        this.iat = token.iat
    }
}