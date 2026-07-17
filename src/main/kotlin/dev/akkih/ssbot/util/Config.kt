package dev.akkih.ssbot.util

import io.github.cdimascio.dotenv.dotenv

class Config {
    private val dotenv = dotenv {
        ignoreIfMissing = true
    }

    val DISCORD_TOKEN = dotenv["DISCORD_TOKEN"] ?: System.getenv("DISCORD_TOKEN")
    val WORKER_BASE_URL = dotenv["WORKER_BASE_URL"] ?: System.getenv("WORKER_BASE_URL")
    val WORKER_UPLOAD_SECRET = dotenv["WORKER_UPLOAD_SECRET"] ?: System.getenv("WORKER_UPLOAD_SECRET")
}