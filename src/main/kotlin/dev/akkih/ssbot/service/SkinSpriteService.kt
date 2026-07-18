package dev.akkih.ssbot.service

import dev.akkih.ssbot.Bot.json
import dev.akkih.ssbot.service.models.ConvertOptions
import dev.akkih.ssbot.service.response.ConvertValue
import dev.akkih.ssbot.service.response.SkinConversionResponse
import dev.akkih.ssbot.util.Links
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.forms.*
import io.ktor.http.*

class SkinSpriteService(private val client: HttpClient) {
    suspend fun convert(skin: ByteArray, options: ConvertOptions = ConvertOptions()): ConvertValue {
        val response = client.submitFormWithBinaryData(
            url = "${Links.Api.SKINSPRITE}/api/convert",
            formData = formData {
                append("options", json.encodeToString(options))
                append("skin", skin, Headers.build {
                    append(HttpHeaders.ContentType, "image/png")
                    append(HttpHeaders.ContentDisposition, "filename=\"skin.png\"")
                })
            }
        ).body<SkinConversionResponse>()

        if (!response.ok) {
            throw IllegalArgumentException("Error in ${Links.Api.SKINSPRITE} request: ${response.error ?: "Unknown error"}")
        }

        return response.value ?: throw IllegalArgumentException("Server returned ok but no image data.")
    }
}