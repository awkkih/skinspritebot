package dev.akkih.ssbot.service.response

import kotlinx.serialization.Serializable

@Serializable
data class CloudflareUploadResponse(
    val key: String,
    val downloadUrl: String,
)