package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.Post
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.statistic.PageWallStatisticImpl

interface PageWallImpl : WallImpl {
    fun pin(): Post?
    fun body(): WallBody
    fun statistic(): PageWallStatisticImpl?
    fun mutual(): Array<ProfileBase>?
    fun list(): Array<Post>?
}