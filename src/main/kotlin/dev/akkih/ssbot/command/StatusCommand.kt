package dev.akkih.ssbot.command

import dev.akkih.ssbot.Bot
import dev.akkih.ssbot.Bot.client
import dev.akkih.ssbot.Bot.healthCheckService
import dev.akkih.ssbot.Bot.log
import dev.akkih.ssbot.util.Colors
import dev.akkih.ssbot.util.Emojis
import dev.akkih.ssbot.util.errorEmbed
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.jdabuilder.scope
import dev.minn.jda.ktx.messages.Embed
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class StatusCommand {
    init {
        client.onCommand("status") { event ->
            event.deferReply().queue()

            val start = System.nanoTime()

            client.scope.launch {
                try {
                    val mcHeads = async { healthCheckService.mcHeadsStatus() }.await()
                    val skinSpriteStudio = async { healthCheckService.skinSpriteStudioStatus() }.await()
                    val cloudflareWorker = async { healthCheckService.cloudflareWorkersStatus() }.await()

                    event.hook.editOriginalEmbeds(
                        Embed {
                            title = "Platform Status"
                            description = "Status of external services. Some features may be unavailable if a service is down."
                            color = Colors.INFO

                            field {
                                name = "MC Heads"
                                value = generateFieldValue(mcHeads)
                                inline = true
                            }

                            field {
                                name = "Skin Sprite Studio"
                                value = generateFieldValue(skinSpriteStudio)
                                inline = true
                            }

                            field {
                                name = "Cloudflare Worker"
                                value = generateFieldValue(cloudflareWorker)
                                inline = true
                            }

                            val took = (System.nanoTime() - start) / 1_000_000_000.0

                            footer {
                                name = "Checked in ${"%.2f".format(took)} s"
                                iconUrl = event.user.effectiveAvatarUrl
                            }
                        }
                    ).queue()
                } catch (ex: Exception) {
                    event.hook.editOriginalEmbeds(errorEmbed(ex.message ?: "Unknown error.")).queue()
                    log.error(ex.message ?: "Unknown error", ex)
                }
            }
        }
    }

    private fun generateFieldValue(ok: Boolean): String {
        return if (ok) {
            "${Emojis.CHECK} Online"
        } else {
            "${Emojis.X} Offline"
        }
    }
}