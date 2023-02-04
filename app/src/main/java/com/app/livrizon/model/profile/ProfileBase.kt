package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl

open class ProfileBase(
    val profile_id: Int,
    val avatar: String? = null,
    val name: String,
    val status: Int
) : ProfileImpl {
    constructor(profile: ProfileBase) : this(
        profile.profile_id,
        profile.avatar,
        profile.name,
        profile.status
    )

    override fun id(): Int? {
        return profile_id
    }

    override fun layout(): Int {
        return ProfileImpl.base
    }
}