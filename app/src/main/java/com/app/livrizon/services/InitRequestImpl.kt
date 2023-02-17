package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.*
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.request.Filter
import com.app.livrizon.request.Selection
import com.app.livrizon.request.Sort
import com.app.livrizon.security.Role

interface InitRequestImpl {
    suspend fun profiles(
        selection: Selection,
        profile_id: Int?,
        filter: Filter?,
        preview: Boolean,
        sort: Sort,
        limit: Int,
        vararg roles: Role
    ): Array<ProfileBase>

    suspend fun messages(profile_id: Int, message_id: Int?): Array<Message>
    suspend fun chat(profile_id: Int): InitChat
    suspend fun chats(): Array<Chat>
    suspend fun articles(
        filter: Filter,
        limit: Int,
        cl: Class<*>
    ): Array<PreviewPublication>

    suspend fun posts(
        profile_id: Int?,
        type: PublicationType?,
        filter: Filter,
        preview: Boolean,
        limit: Int
    ): Array<Post>
}