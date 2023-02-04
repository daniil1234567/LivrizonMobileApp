package com.app.livrizon.model.response

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val response: ResponseState
)