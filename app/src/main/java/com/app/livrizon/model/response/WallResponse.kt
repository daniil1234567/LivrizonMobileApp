package com.app.livrizon.model.response

import com.app.livrizon.security.Role

class WallResponse(
    val role: Role,
    val status: Int,
    val available: Boolean,
    val body: String,
) : java.io.Serializable