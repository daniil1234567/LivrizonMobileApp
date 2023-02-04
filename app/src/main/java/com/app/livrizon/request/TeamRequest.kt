package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.edit.profile.save.TeamSave
import com.app.livrizon.services.TeamService
import com.app.livrizon.values.*
import io.ktor.client.request.*

object TeamRequest : TeamService {
    override suspend fun saveTeam(save: TeamSave): Chat {
        return gson.fromJson(httpClient.post<String> {
            url(HttpRoutes.team)
            body = gson.toJson(save)
            headers.append(Parameters.auth, token.jwt)
        }, Chat::class.java)
    }
}