package com.app.livrizon.services

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.*
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.request.Filter
import com.app.livrizon.request.Selection
import com.app.livrizon.security.Role

interface InitRequestImpl {
    suspend fun profilesSub(
        selection: Selection,
        filter: Filter?,
        profile_id: Int,
        cl: Class<*>
    ): Array<ProfileBase>
    suspend fun profiles(role: Role?, filter: Filter, my_sub: Boolean?, limit: Int): Array<Profile>
    suspend fun sub(selection: Selection, profile_id: Int, filter: Filter?): Array<Subscribe>
    suspend fun messages(profile_id: Int, message_id: Int?): Array<Message>
    suspend fun chat(profile_id: Int): InitChat
    suspend fun chats(): Array<Chat>
    suspend fun append(): Array<Profile>
    suspend fun visits(): Array<ProfileBase>
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