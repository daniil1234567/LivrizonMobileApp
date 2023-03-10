package com.app.livrizon.request

import com.app.livrizon.model.edit.topic.TopicEdit
import com.app.livrizon.model.response.Response
import com.app.livrizon.model.wall.Wall
import com.app.livrizon.model.wall.WallInformation
import com.app.livrizon.services.ProfileRequestImpl
import com.app.livrizon.values.*
import io.ktor.client.request.*

object ProfileRequest : ProfileRequestImpl {

    override suspend fun sub(profile_id: Int): Response {
        return httpClient.put {
            url(HttpRoutes.sub(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }
    }

    override suspend fun topics(topics: Array<TopicEdit>): Response {
        return httpClient.put {
            url(HttpRoutes.topics)
            headers.append(Parameters.auth, token.jwt)
            body = gson.toJson(topics)
        }
    }

    override suspend fun information(profile_id: Int): WallInformation {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.information(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }, WallInformation::class.java)
    }

    override suspend fun wall(profile_id: Int): Wall {
        return gson.fromJson(httpClient.get<String> {
            url(HttpRoutes.wall(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }, Wall::class.java)
    }

    override suspend fun putInterest(append: List<Int>?, delete: List<Int>?) {
        httpClient.put<Response> {
            url(HttpRoutes.interests)
            headers.append(Parameters.auth, token.jwt)
            if (append != null)
                for (i in append.indices) {
                    parameter(Parameters.append, append[i].toString())
                }
            if (delete != null)
                for (i in delete.indices) {
                    parameter(Parameters.delete, delete[i].toString())
                }
        }
    }

    override suspend fun hideRecent(profile_id: Int):Response {
        return httpClient.delete{
            url(HttpRoutes.visit(profile_id))
            headers.append(Parameters.auth, token.jwt)
        }
    }


}