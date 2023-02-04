package com.app.livrizon.function

import java.util.*

object Encrypt {
    fun base64Decode(str: String): String {
        val decoder: Base64.Decoder = Base64.getDecoder()
        val decoded: ByteArray = decoder.decode(str.toByteArray())
        return String(decoded)
    }
}