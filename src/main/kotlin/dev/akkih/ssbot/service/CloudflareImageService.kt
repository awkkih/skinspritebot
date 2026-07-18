package dev.akkih.ssbot.service

import dev.akkih.ssbot.Bot.config
import dev.akkih.ssbot.service.response.CloudflareUploadResponse
import dev.akkih.ssbot.util.userError
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class CloudflareImageService(private val client: HttpClient) {
    suspend fun uploadFile(image: ByteArray): String {
        val response = client.post("${config.WORKER_BASE_URL}/upload") {
            header("x-upload-secret", config.WORKER_UPLOAD_SECRET)
            contentType(ContentType.Image.PNG)
            setBody(image)
        }.body<CloudflareUploadResponse>()

        if (response.downloadUrl.isEmpty()) {
            userError("'downloadUrl' is missing from response body.")
        }

        return response.downloadUrl
    }
}