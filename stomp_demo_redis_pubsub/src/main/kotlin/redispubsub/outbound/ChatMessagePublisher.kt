package redispubsub.outbound

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import redispubsub.ChatMessageWithRoom
import redispubsub.inbound.ChatMessage

@Service
class ChatMessagePublisher(
    private val redisTemplate: StringRedisTemplate
) {
    private val objectMapper = jacksonObjectMapper().registerKotlinModule()

    fun publishMessage(roomId: String, chatMessage: ChatMessage) {
        val message = ChatMessageWithRoom(roomId, chatMessage.sender, chatMessage.content)

        redisTemplate.convertAndSend("chatroom",
            objectMapper.writeValueAsString(message)
        )
    }
}

