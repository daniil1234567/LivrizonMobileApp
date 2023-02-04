package com.app.livrizon.request

import com.app.livrizon.model.response.Response
import com.app.livrizon.services.EventService
import com.app.livrizon.values.HttpRoutes
import com.app.livrizon.values.Parameters
import com.app.livrizon.values.token
import com.app.livrizon.values.httpClient
import io.ktor.client.request.*

object EventRequest : EventService {
    override suspend fun seen(from: Int, publication_id: Int):Response {
        return httpClient.post {
            url(HttpRoutes.seen)
            parameter(Parameters.id, publication_id)
            parameter(Parameters.from, from)
            headers.append(Parameters.auth, token!!.jwt)
        }
    }
}