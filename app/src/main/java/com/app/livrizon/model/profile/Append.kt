package com.app.livrizon.model.profile

import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

class Append(
    val important: Boolean,
    var choose: Boolean,
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
    status: Int,
) : Profile(last, title, followers, city, my_sub, profile_id, avatar, name, confirm, role, status),
    ChooseImpl {
    override fun choose(): Boolean {
        return choose
    }

    constructor(important: Boolean, profile: Profile) : this(
        important, false, profile.last,
        profile.title, profile.followers, profile.city, profile.my_sub, profile.profile_id,
        profile.avatar, profile.name, profile.confirm, profile.role, profile.status
    )

    override fun layout(): Int {
        return ProfileImpl.append
    }
}