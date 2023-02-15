package com.app.livrizon.security.token

import com.app.livrizon.function.Encrypt
import com.app.livrizon.model.type.TokenType

abstract class TokenBase(val jwt: String) : java.io.Serializable {

    lateinit var type: TokenType
    var iat: Long = 0
    var exp: Long = 0
    fun id():Int{
        return (this as AccessToken).id
    }
    protected fun claims(): String {
        return Encrypt.base64Decode(jwt.split(".")[0])
    }

}