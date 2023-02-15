package com.app.livrizon.model.authorization

import com.app.livrizon.function.Encrypt
import com.app.livrizon.model.type.TokenType
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.gson
import kotlinx.serialization.Serializable

@Serializable
data class Jwt(
    val jwt: String
) {
    fun type():TokenType {
        val claims = gson.fromJson(Encrypt.base64Decode(jwt.split(".")[0]), Map::class.java)
        return TokenType.valueOf(claims[Parameters.type] as String)
    }
}