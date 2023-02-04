package com.app.livrizon.model.wall.relation

import com.app.livrizon.model.type.MemberType

class TeamWallRelation(
    val append: Boolean,
    type: MemberType,
    edit: Boolean,
    invite: Boolean,
    write: Boolean,
    chat: Boolean,
    it_res: Boolean,
    my_sub: Boolean
) : PublicWallRelation(type, edit, invite, write, chat, it_res, my_sub)