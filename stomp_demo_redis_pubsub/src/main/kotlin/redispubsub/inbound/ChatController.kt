package redispubsub.inbound

import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import redispubsub.outbound.ChatMessagePublisher

data class ChatMessage(
    val sender: String,
    val content: String
)

private val log = KotlinLogging.logger {}

@Controller
class ChatController(
    private val chatMessagePublisher: ChatMessagePublisher
) {
    @MessageMapping("/chatroom/{roomId}")
    fun sendMessage(@DestinationVariable roomId: String, message: ChatMessage) {
        log.info { "Received message: $message" }
        chatMessagePublisher.publishMessage(roomId, message)
    }
}