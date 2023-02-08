package com.app.livrizon.model.chat

import com.app.livrizon.model.wall.relation.WallRelationBase

class ChatRelation(
    val attached: Boolean,
    write: Boolean,
    chat: Boolean,
    it_res: Boolean,
    my_sub: Boolean
) : WallRelationBase(write, chat, it_res, my_sub)