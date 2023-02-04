package com.app.livrizon.request

import com.app.livrizon.model.response.Response
import com.app.livrizon.model.edit.publication.SaveMessage
import com.app.livrizon.services.ChatService
import com.app.livrizon.values.*
import io.ktor.client.request.*

object ChatRequest : ChatService {
    override suspend fun deleteMessages(profile_id: Int, ids: MutableList<Int>): Response {
        return httpClient.delete {
            url(HttpRoutes.messages(profile_id))
            headers.append(Parameters.auth, token.jwt)
            for (i in 0 until ids.size) {
                parameter(Parameters.id, ids[i])
            }
        }
    }

    override suspend fun sendMessage(
        profile_id: Int,
        message: SaveMessage,
    ): Response {
        return httpClient.post {
            url(HttpRoutes.message(profile_id))
            headers.append(Parameters.auth, token.jwt)
            if (message.forward != null) {
                parameter(Parameters.reply, message.forward.reply)
                parameter(Parameters.from, message.forward.from)
                for (i in 0 until message.forward.publications.size) {
                    parameter(Parameters.forward, message.forward.publications[i])
                }
            }
            body = gson.toJson(message)
        }
    }


}