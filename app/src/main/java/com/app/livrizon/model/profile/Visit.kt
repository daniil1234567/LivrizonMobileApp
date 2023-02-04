package com.app.livrizon.model.profile

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ProfileImpl

class Visit(
    val visit_id: Int,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
) : ProfileBase(profile_id, avatar, name, status) {
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