package com.app.livrizon.model.profile

import com.app.livrizon.model.type.SubscribeType
import com.app.livrizon.security.Role

class Subscribe(
    type: SubscribeType,
    scroll_id: Int,
    order: Int,
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
) : ScrollProfile(
    scroll_id,
    order,
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
)