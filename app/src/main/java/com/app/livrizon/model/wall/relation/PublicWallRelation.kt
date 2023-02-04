package com.app.livrizon.model.wall.relation

import com.app.livrizon.model.type.MemberType

open class PublicWallRelation(
    val type: MemberType,
    val edit: Boolean,
    val invite: Boolean,
    write: Boolean,
    chat: Boolean,
    it_res: Boolean,
    my_sub: Boolean
) : WallRelationBase(write, chat, it_res, my_sub)