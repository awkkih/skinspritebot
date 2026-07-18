package dev.akkih.ssbot.util

import dev.akkih.ssbot.Bot.client

object Emojis {
    val ARROW = client.retrieveApplicationEmojiById(1527817473905393894).complete().asMention
    val CHECK = client.retrieveApplicationEmojiById(1527817484286296275).complete().asMention
    val X = client.retrieveApplicationEmojiById(1527817482880942170).complete().asMention
    val HEARTS = client.retrieveApplicationEmojiById(1527817477017440377).complete().asMention
}