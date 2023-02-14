package com.app.livrizon.model.wall.relation

import com.app.livrizon.model.profile.relation.ProfileRelation

class WallRelation(
    val gen: Int?=null,
    val it_sub: Boolean,
    val privileged: Boolean,
    val chat: Boolean,
    val visibility: Int,
    val available: Boolean,
    val edit: Boolean,
    val invite: Boolean,
    val append: Boolean,
    my_sub: Boolean,
    my_res: Boolean,
    it_res: Boolean,
    attached: Boolean,
    write: Boolean,
) : ProfileRelation(my_sub, my_res, it_res, attached, write)