package com.app.livrizon.model.authorization

import com.app.livrizon.model.type.ContactType
import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val registration: ContactType
)