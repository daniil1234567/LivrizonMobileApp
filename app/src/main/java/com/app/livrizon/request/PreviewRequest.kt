package com.app.livrizon.request

import com.app.livrizon.model.chat.Chat
import com.app.livrizon.services.PreviewService
import com.app.livrizon.values.*
import io.ktor.client.request.*

object PreviewRequest : PreviewService {
    override suspend fun chat(profile_id: Int): Chat {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.previewChat(profile_id))
            headers.append(Parameters.auth, token!!.jwt)
        }, Chat::class.java)
    }
}