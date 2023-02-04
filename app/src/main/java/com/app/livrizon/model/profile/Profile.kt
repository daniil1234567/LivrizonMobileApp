package com.app.livrizon.model.profile

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

open class Profile(
    val last: Long? = null,
    val title: String? = null,
    val followers: Int,
    val city: String? = null,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
) : Owner(confirm, role, profile_id, avatar, name, status),Base {
    override fun id(): Int {
        return profile_id
    }

    override fun layout(): Int {
        return ProfileImpl.profile
    }
}