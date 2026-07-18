package dev.akkih.ssbot.service

import dev.akkih.ssbot.util.Commands
import dev.akkih.ssbot.util.Links
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MinecraftSkinService(private val client: HttpClient) {
    suspend fun fetchSkin(username: String): ByteArray {
        val response = client.get("${Links.Api.MCHEADS}/skin/$username")

        if (!response.status.isSuccess()) {
            throw IllegalArgumentException("Failed to fetch skin for '$username' from MC Heads API.\n\nCheck its availability with ${Commands.STATUS}.")
        }

        val contentType = response.contentType()
        if (contentType == null || !contentType.match(ContentType.Image.PNG)) {
            throw IllegalArgumentException("Unexpected response fetching skin for '$username'.\n\nReport this to the developers at ${Links.GH_ISSUES}.")
        }

        return response.body()
    }
}