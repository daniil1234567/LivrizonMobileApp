package com.app.livrizon.model.profile

import com.app.livrizon.impl.Base
import com.app.livrizon.impl.ProfileImpl
import com.app.livrizon.security.Role

open class Profile(
    val last: Long? = null,
    val title: String? = null,
    val followers: Int? = null,
    val city: String? = null,
    var my_sub: Int,
    var my_res: Boolean,
    var write: Boolean,
    var gen: Int? = null,
    profile_id: Int,
    avatar: String? = null,
    name: String,
    confirm: Boolean,
    role: Role,
    status: Int,
) : ProfileBase(profile_id, avatar, name, confirm, role, status), Base {

    override fun id(): Int {
        return profile_id
    }

    override fun layout(): Int {
        return ProfileImpl.profile
    }
}