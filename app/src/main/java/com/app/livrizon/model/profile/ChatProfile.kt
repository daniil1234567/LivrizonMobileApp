package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class ChatProfile (
    val last: Long? = null,
    val followers: Int? = null,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
): ProfileBase(profile_id, avatar, name, confirm, role, status) {
    override fun layout(): Int {
        return 0
    }
    constructor(profile: Profile):this(profile.last,profile.followers,profile.profile_id,profile.avatar,profile.name,profile.confirm,profile.role,profile.status)
}