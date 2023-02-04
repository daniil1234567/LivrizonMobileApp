package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

class Account(
    val username: String,
    last: Long? = null,
    title: String? = null,
    followers: Int,
    city: String? = null,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
) : Profile(last, title, followers, city, confirm, role, profile_id, avatar, name, status)