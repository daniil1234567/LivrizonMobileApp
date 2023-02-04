package com.app.livrizon.model.profile

import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.model.type.MemberType
import com.app.livrizon.security.Role

class Member(
    val member: MemberType,
    subscribe_id: Int,
    order: Boolean,
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
) : Subscribe(subscribe_id, order, last, title, followers, city, confirm, role, profile_id, avatar, name, status){
    override fun layout(): Int {
        return ProfileImpl.member
    }
}