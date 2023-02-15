package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class ChatProfile(
    val followers: Int? = null,
    last: Long? = null,
    open: Boolean,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int
) : WallProfile(last, open, profile_id, avatar, name, confirm, role, status) {
    override fun layout(): Int {
        return 0
    }
}