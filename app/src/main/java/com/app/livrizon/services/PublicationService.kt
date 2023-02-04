package com.app.livrizon.services

import com.app.livrizon.model.edit.publication.SavePost
import io.ktor.http.*

interface PublicationService {
    suspend fun savePost(savePost: SavePost)
    suspend fun like(type: HttpMethod, post_id: Int)
}