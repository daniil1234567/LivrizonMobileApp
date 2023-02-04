package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

open class Owner (
    val confirm:Boolean,
    val role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
): ProfileBase(profile_id, avatar, name, status)