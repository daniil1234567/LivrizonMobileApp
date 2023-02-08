package com.app.livrizon.services

import com.app.livrizon.model.edit.publication.SavePost
import com.app.livrizon.model.response.Response
import io.ktor.http.*

interface PublicationRequestImpl {
    suspend fun seen(from: Int, publication_id: Int): Response
    suspend fun savePost(savePost: SavePost)
    suspend fun like(type: HttpMethod, post_id: Int)
}