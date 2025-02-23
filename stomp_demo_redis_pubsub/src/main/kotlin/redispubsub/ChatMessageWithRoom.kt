package redispubsub

data class ChatMessageWithRoom(
    val roomId: String,
    val sender: String,
    val content: String
)