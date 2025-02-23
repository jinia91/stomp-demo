package simplebroker

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.stereotype.Controller

private val log = mu.KotlinLogging.logger {}

@Controller
class ChatController {
    @MessageMapping("/chatroom/{roomId}")
    @SendTo("/topic/room/{roomId}")
    fun sendMessage(@DestinationVariable roomId: String, message: ChatMessage): ChatMessage {
        log.info { "Received message: $message" }
        return message
    }
}
data class ChatMessage(
    val sender: String,
    val content: String
)
