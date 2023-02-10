package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

open class Subscribe(
    val subscribe_id: Int,
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
) : Profile(last, title, followers, city, my_sub, profile_id, avatar, name, confirm, role, status) {
    override fun id(): Int {
        return subscribe_id
    }

    override fun equals(): Int {
        return profile_id
    }
    override fun layout(): Int {
        return ProfileImpl.profile
    }
}