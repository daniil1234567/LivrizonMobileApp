package com.app.livrizon.model.wall.relation

abstract class WallRelationBase(
    val write: Boolean,
    val chat: Boolean,
    val it_res: Boolean,
    var my_sub: Boolean,
) : java.io.Serializable