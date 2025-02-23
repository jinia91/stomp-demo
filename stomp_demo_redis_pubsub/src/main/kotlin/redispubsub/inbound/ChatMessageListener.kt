package redispubsub.inbound

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service
import redispubsub.ChatMessageWithRoom

@Service
class ChatMessageListener(
    private val messagingTemplate: SimpMessagingTemplate
) : MessageListener {
    private val objectMapper = jacksonObjectMapper().registerKotlinModule()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val chatMessage = objectMapper.readValue(message.body, ChatMessageWithRoom::class.java)
        messagingTemplate.convertAndSend("/topic/room/${chatMessage.roomId}",
            ChatMessage(chatMessage.sender, chatMessage.content))
    }
}