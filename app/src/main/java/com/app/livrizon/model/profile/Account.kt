package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.impl.ProfileImpl.Companion.profile
import com.app.livrizon.security.Role

class Account(
    val username: String,
    val title: String,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
) : ProfileBase(profile_id, avatar, name, confirm, role, status) {
    override fun layout(): Int {
        return profile
    }
}