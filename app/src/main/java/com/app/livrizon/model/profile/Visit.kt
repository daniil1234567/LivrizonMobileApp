package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

class Visit(
    val visit_id: Int,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
) : ProfileBase(profile_id, avatar, name, confirm, role, status) {
    override fun id(): Int {
        return visit_id
    }

    override fun equals(): Int {
        return profile_id
    }

    override fun layout(): Int {
        return ProfileImpl.base
    }
}