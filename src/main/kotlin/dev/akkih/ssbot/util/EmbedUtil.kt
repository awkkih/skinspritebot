package dev.akkih.ssbot.util

import dev.minn.jda.ktx.messages.Embed

fun errorEmbed(message: String) = Embed {
    title = "${Emojis.X} An error occurred!"
    description = message
    color = Colors.ERROR

    footer {
        name = "If this is a server error, contact me at @akkih_ (Twitter)"
    }
}