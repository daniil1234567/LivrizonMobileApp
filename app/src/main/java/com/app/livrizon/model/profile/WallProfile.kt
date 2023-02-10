package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class WallProfile(
    val last: Long,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
) : ProfileBase(profile_id, avatar, name, confirm, role, status)