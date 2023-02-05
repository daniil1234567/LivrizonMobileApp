package com.app.livrizon.model.wall

import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.WallProfile
import com.app.livrizon.model.publication.Post
import com.app.livrizon.model.wall.body.WallBody
import com.app.livrizon.model.wall.relation.PublicWallRelation
import com.app.livrizon.model.wall.statistic.CommunityWallStatistic

class CommunityWall(
    val pin: Post? = null,
    wallpaper: String? = null,
    body: WallBody,
    mutual: Array<ProfileBase>? = null,
    profile: WallProfile,
    override val statistic: CommunityWallStatistic? = null,
    override val list: Array<Post>? = null,
    override val relation: PublicWallRelation
) : Wall(wallpaper, body, mutual, profile), PageWallImpl {
    override fun pin(): Post? {
        return pin
    }

    override fun body(): WallBody {
        return body;
    }

    override fun statistic(): CommunityWallStatistic? {
        return statistic
    }

    override fun mutual(): Array<ProfileBase>? {
        return mutual
    }

    override fun list(): Array<Post>? {
        return list
    }
}