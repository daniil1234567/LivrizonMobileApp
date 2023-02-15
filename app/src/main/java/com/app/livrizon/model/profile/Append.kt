package com.app.livrizon.model.profile

import com.app.livrizon.impl.ChooseImpl
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

class Append(
    var important: Boolean = false,
    var choose: Boolean = false,
    last: Long? = null,
    title: String? = null,
    followers: Int? = null,
    city: String? = null,
    my_sub: Int,
    my_res: Boolean,
    write: Boolean,
    gen: Int? = null,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int
) : Profile(
    last,
    title,
    followers,
    city,
    my_sub,
    my_res,
    write,
    gen,
    profile_id,
    avatar,
    name,
    confirm,
    role,
    status
), ChooseImpl {
    constructor(important: Boolean, profile: Profile) : this(
        important,
        false,
        profile.last,
        profile.title,
        profile.followers,
        profile.city,
        profile.my_sub,
        profile.my_res,
        profile.write,
        profile.gen,
        profile.profile_id,
        profile.avatar,
        profile.name,
        profile.confirm,
        profile.role,
        profile.status
    )

    override fun choose(): Boolean {
        return choose
    }

    override fun layout(): Int {
        return ProfileImpl.append
    }
}