package com.app.livrizon.model.wall.relation

class AccountWallRelation(
    val it_subscribe: Boolean,
    val my_restrict: Boolean,
    val favorite: Boolean,
    val gen: Int? = null,
    chat: Boolean,
    write: Boolean,
    it_res: Boolean,
    my_sub: Boolean
) : WallRelationBase(write, chat, it_res, my_sub)