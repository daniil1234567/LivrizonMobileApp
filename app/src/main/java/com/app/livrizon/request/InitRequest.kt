package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.ProfileBase
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.*
import com.app.livrizon.model.type.PublicationType
import com.app.livrizon.security.Role
import com.app.livrizon.services.InitRequestImpl
import com.app.livrizon.values.*
import io.ktor.client.request.*

object InitRequest : InitRequestImpl {

    override suspend fun profiles(
        role: Role?,
        filter: Filter,
        my_sub: Boolean?,
        limit: Int
    ): Array<Profile> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_profiles)
            parameter(Parameters.role, role)
            parameter(Parameters.filter, filter)
            parameter(Parameters.my_sub, my_sub)
            parameter(Parameters.limit, limit)
            headers.append(Parameters.auth, token.jwt)
        },Array<Profile>::class.java)
    }


    override suspend fun sub(
        sub: Sub,
        profile_id: Int,
        filter: Filter?
    ): Array<Subscribe> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initSub(sub, profile_id))
            if (filter != null) parameter(Parameters.filter, filter)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Subscribe>::class.java)
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

    override suspend fun append(): Array<Profile> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_append)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Profile>::class.java)
    }

    override suspend fun visits(): Array<ProfileBase> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_visits)
            headers.append(Parameters.auth, token.jwt)
        }, Array<ProfileBase>::class.java)
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