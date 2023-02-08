package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.init.*
import com.app.livrizon.model.profile.Profile
import com.app.livrizon.model.profile.Subscribe
import com.app.livrizon.model.publication.Message
import com.app.livrizon.model.publication.Post
import com.app.livrizon.services.InitRequestImpl
import com.app.livrizon.values.*
import io.ktor.client.request.*

object InitRequest : InitRequestImpl {
    override suspend fun services(): ServiceInit {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_services)
            headers.append(Parameters.auth, token.jwt)
        }, ServiceInit::class.java)
    }

    override suspend fun profileSearch(): InitProfileSearch {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_profile_search)
            headers.append(Parameters.auth, token.jwt)
        }, InitProfileSearch::class.java)
    }

    override suspend fun profiles(selection: Selection, my_sub: Boolean?): Array<Profile> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initProfile(selection))
            parameter(Parameters.my_sub, my_sub)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Profile>::class.java)
    }

    override suspend fun sub(
        selection: Selection,
        profile_id: Int,
        filter: Filter?
    ): Array<Subscribe> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initSub(selection, profile_id))
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

    override suspend fun chat(profile_id: Int): InitChat {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.initChat(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }, InitChat::class.java)
    }

    override suspend fun news(): InitNews {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_news)
            headers.append(Parameters.auth, token.jwt)
        }, InitNews::class.java)
    }

    override suspend fun home(): Array<Post> {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.init_home)
            headers.append(Parameters.auth, token.jwt)
        }, Array<Post>::class.java)
    }

}