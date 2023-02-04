package com.app.livrizon.services

import com.app.livrizon.model.response.Response

interface EventService {
    suspend fun seen(from: Int, publication_id: Int): Response
}