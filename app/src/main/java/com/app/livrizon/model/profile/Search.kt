package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class Search(
    val order: Int,
    last: Long? = null,
    title: String? = null,
    followers: Int? = null,
    city: String? = null,
    my_sub: Int,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
) : Profile(last, title, followers, city, my_sub, profile_id, avatar, name, confirm, role, status)