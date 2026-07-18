package dev.akkih.ssbot.command

import dev.akkih.ssbot.Bot
import dev.akkih.ssbot.Bot.client
import dev.akkih.ssbot.util.Colors
import dev.akkih.ssbot.util.Commands
import dev.akkih.ssbot.util.Emojis
import dev.akkih.ssbot.util.Links
import dev.minn.jda.ktx.events.onCommand
import dev.minn.jda.ktx.interactions.components.link
import dev.minn.jda.ktx.interactions.components.row
import dev.minn.jda.ktx.messages.Embed

class InfoCommand {
    init {
        client.onCommand("info") { event ->
            event.replyEmbeds(
                Embed {
                    title = "Skin Sprite Bot"
                    description = """
                        Turns Minecraft skins into rendered character sprites.
                        
                        **Commands**
                        ${Emojis.ARROW} ${Commands.SPRITE_FILE}
                        ${Emojis.ARROW} ${Commands.SPRITE_USERNAME}
                        ${Emojis.ARROW} ${Commands.STATUS}
                        
                        Built by akkih and powered by [Skin Sprite Studio](${Links.Api.SKINSPRITE}) and [MCHeads](${Links.Api.MCHEADS}). ${Emojis.HEARTS}
                    """.trimIndent()
                    color = Colors.INFO
                    thumbnail = Bot.thumbnailUrl

                    footer {
                        name = "Thanks for using the bot!"
                        iconUrl = event.user.effectiveAvatarUrl
                    }
                }
            ).addComponents(
                row(
                    link(Links.Api.SKINSPRITE, "Original Website"),
                    link(Links.GH_PAGE, "GitHub Repository"),
                )
            ).queue()
        }
    }
}