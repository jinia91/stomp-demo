package redispubsub
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import redispubsub.inbound.ChatMessageListener

@Configuration
class RedisConfig {
    @Bean
    fun topic(): ChannelTopic {
        return ChannelTopic("chatroom")
    }

    @Bean
    fun redisMessageListenerContainer(
        connectionFactory: RedisConnectionFactory,
        listenerAdapter: MessageListenerAdapter
    ): RedisMessageListenerContainer {
        return RedisMessageListenerContainer().apply {
            setConnectionFactory(connectionFactory)
            addMessageListener(listenerAdapter, PatternTopic("chatroom"))
        }
    }

    @Bean
    fun listenerAdapter(chatMessageListener: ChatMessageListener): MessageListenerAdapter {
        return MessageListenerAdapter(chatMessageListener, "handleMessage")
    }
}