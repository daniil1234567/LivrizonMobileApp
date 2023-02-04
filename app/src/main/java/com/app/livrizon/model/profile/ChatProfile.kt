package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class ChatProfile (
    val last: Long? = null,
    val title: String? = null,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int,
): Owner(confirm, role, profile_id, avatar, name, status){
    override fun layout(): Int {
        return 0
    }
}