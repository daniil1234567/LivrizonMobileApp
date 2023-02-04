package com.app.livrizon.model.profile

import com.app.livrizon.security.Role

class Like(
    val like_id: Int,
    val order: Boolean,
    last: Long? = null,
    title: String? = null,
    followers: Int,
    city: String? = null,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int
) : Profile(last, title, followers, city, confirm, role, profile_id, avatar, name, status){
    override fun id(): Int {
        return like_id
    }

    override fun equals(): Int {
        return profile_id
    }
}