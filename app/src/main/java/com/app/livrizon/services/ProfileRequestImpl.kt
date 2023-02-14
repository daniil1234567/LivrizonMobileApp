package com.app.livrizon.services

import com.app.livrizon.model.edit.topic.TopicEdit
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.wall.Wall
import com.app.livrizon.model.wall.WallInformation

interface ProfileRequestImpl {
    suspend fun sub(profile_id: Int): Response
    suspend fun topics(topics: Array<TopicEdit>): Response
    suspend fun information(profile_id: Int): WallInformation
    suspend fun wall(profile_id: Int): Wall
    suspend fun putInterest(append: List<Int>?, delete: List<Int>?)
    suspend fun hideRecent(profile_id: Int): Response
}