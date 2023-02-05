package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

open class Subscribe(
    val subscribe_id: Int,
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
) : Profile(last, title, followers, city, my_sub, confirm, role, profile_id, avatar, name, status) {
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