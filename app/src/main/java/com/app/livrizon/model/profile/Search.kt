package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class Search(
    val order: Int,
    last: Long? = null,
    title: String? = null,
    followers: Int,
    city: String? = null,
    my_sub: Boolean,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int,
) : Profile(last, title, followers, city, my_sub, confirm, role, profile_id, avatar, name, status)