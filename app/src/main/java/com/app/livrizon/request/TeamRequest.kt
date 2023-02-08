package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.model.edit.profile.save.TeamSave
import com.app.livrizon.model.response.Response
import com.app.livrizon.services.TeamRequestImpl
import com.app.livrizon.values.*
import io.ktor.client.request.*

object TeamRequest : TeamRequestImpl {
    override suspend fun saveTeam(save: TeamSave): Response {
        return gson.fromJson(httpClient.post<String> {
            url(HttpRoutes.team)
            body = gson.toJson(save)
            headers.append(Parameters.auth, token.jwt)
        }, Response::class.java)
    }
}