package com.app.livrizon.model.profile

import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

class Append(
    val important: Boolean,
    var choose: Boolean,
    last: Long? = null,
    title: String? = null,
    followers: Int,
    city: String? = null,
    my_sub: Boolean,
    confirm: Boolean,
    role: Role,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    status: Int,
) : Profile(last, title, followers, city, my_sub, confirm, role, profile_id, avatar, name, status),ChooseImpl {
    override fun choose(): Boolean {
        return choose
    }
    constructor(important: Boolean,profile: Profile):this(important,false,profile.last,
        profile.title,profile.followers,profile.city,profile.my_sub,profile.confirm,
        profile.role,profile.profile_id,profile.avatar,profile.name,profile.status)

    override fun layout(): Int {
        return ProfileImpl.append
    }
}