package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class WallProfile(
    val last: Long,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
) : Owner(confirm, role, profile_id, avatar, name, status)