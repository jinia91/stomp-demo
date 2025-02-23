package redispubsub.inbound

import org.springframework.messaging.handler.annotation.DestinationVariable
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import redispubsub.outbound.ChatMessagePublisher

data class ChatMessage(
    val sender: String,
    val content: String
)

@Controller
class ChatController(
    private val chatMessagePublisher: ChatMessagePublisher
) {
    @MessageMapping("/chatroom/{roomId}")
    fun sendMessage(@DestinationVariable roomId: String, message: ChatMessage) {
        chatMessagePublisher.publishMessage(roomId, message)
    }
}