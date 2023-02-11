package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.*
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
        return httpClient.put {
            url(HttpRoutes.init_profiles)
            parameter(Parameters.role,role)
            parameter(Parameters.filter,filter)
            parameter(Parameters.my_sub,my_sub)
            parameter(Parameters.limit,limit)
            headers.append(Parameters.auth, token.jwt)
        }
    }
    override suspend fun profileSearch(): InitProfileSearch {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_profile_search)
            headers.append(Parameters.auth, token.jwt)
        }, InitProfileSearch::class.java)
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

    override suspend fun messenger(): InitMessenger {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_messenger)
            headers.append(Parameters.auth, token.jwt)
        }, InitMessenger::class.java)
    }

    override suspend fun articles(): Array<Article> {
        TODO("Not yet implemented")
    }

    override suspend fun authors(): Array<Author> {
        TODO("Not yet implemented")
    }

    override suspend fun popular(): Popular? {
        TODO("Not yet implemented")
    }
    override suspend fun home(): Array<Post> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_home)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Post>::class.java)
    }

}