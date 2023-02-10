package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.model.type.MemberType
import com.app.livrizon.security.Role

class Member(
    val member: MemberType,
    subscribe_id: Int,
    last: Long? = null,
    title: String? = null,
    followers: Int? = null,
    city: String? = null,
    my_sub: Int,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int
) : Subscribe(
    subscribe_id,
    last,
    title,
    followers,
    city,
    my_sub,
    profile_id,
    avatar,
    name,
    confirm,
    role,
    status
) {
    override fun layout(): Int {
        return ProfileImpl.member
    }
}