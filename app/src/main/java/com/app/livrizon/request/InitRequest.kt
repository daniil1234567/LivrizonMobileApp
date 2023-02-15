package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.publication.*
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.security.Role
import com.app.livrizon.services.InitRequestImpl
import com.app.livrizon.values.*
import io.ktor.client.request.*

object InitRequest : InitRequestImpl {
    override suspend fun profiles(
        selection: Selection?,
        profile_id: Int?,
        role: Role?,
        filter: Filter?,
        preview: Boolean,
        sort: Sort,
        limit: Int,
        cl: Class<*>
    ): Array<ProfileBase> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_profiles)
            parameter(Parameters.selection, selection)
            parameter(Parameters.role, role)
            parameter(Parameters.filter, filter)
            parameter(Parameters.sort, sort)
            parameter(Parameters.limit, limit)
            headers.append(Parameters.auth, token.jwt)
        }, cl) as Array<ProfileBase>
    }

    override suspend fun messages(profile_id: Int, message_id: Int?): Array<Message> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initMessages(profile_id))
            if (message_id != null) parameter(Parameters.id, message_id)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Message>::class.java)
    }

    override suspend fun chat(profile_id: Int): InitChat {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initChat(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }, InitChat::class.java)
    }

    override suspend fun chats(): Array<Chat> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_chats)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Chat>::class.java)
    }

    override suspend fun articles(
        filter: Filter,
        limit: Int,
        cl: Class<*>
    ): Array<PreviewPublication> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_posts)
            headers.append(Parameters.auth, token.jwt)
            parameter(Parameters.filter, filter)
            parameter(Parameters.type, PublicationType.article)
            parameter(Parameters.preview, true)
            parameter(Parameters.limit, limit)
        }, cl) as Array<PreviewPublication>
    }

    override suspend fun posts(
        profile_id: Int?,
        type: PublicationType?,
        filter: Filter,
        preview: Boolean,
        limit: Int
    ): Array<Post> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_posts)
            headers.append(Parameters.auth, token.jwt)
            parameter(Parameters.profile_id, profile_id)
            parameter(Parameters.filter, filter)
            parameter(Parameters.preview, preview)
            parameter(Parameters.limit, limit)
        }, Array<Post>::class.java)
    }

}