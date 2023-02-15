package com.app.livrizon.request

import com.app.livrizon.model.response.Response
import com.app.livrizon.model.edit.publication.SavePost
import com.app.livrizon.services.PublicationRequestImpl
import com.app.livrizon.values.*
import com.app.livrizon.values.Parameters
import io.ktor.client.request.*
import io.ktor.http.*

object PublicationRequest : PublicationRequestImpl {
    override suspend fun seen(from: Int, publication_id: Int):Response {
        return httpClient.post {
            url(HttpRoutes.seen)
            parameter(Parameters.id, publication_id)
            parameter(Parameters.from, from)
            headers.append(Parameters.auth, token.jwt)
        }
    }
    override suspend fun like(
        type: HttpMethod,
        post_id: Int
    ) {
        httpClient.request<Response> {
            method = type
            url(HttpRoutes.post_like)
            parameter(Parameters.id, post_id)
            headers.append(Parameters.auth, token.jwt)
        }
    }



    override suspend fun savePost(savePost: SavePost) {
        httpClient.post<Response> {
            url(HttpRoutes.post)
            headers.append(Parameters.auth, token.jwt)
            body = gson.toJson(savePost)
        }
    }

}