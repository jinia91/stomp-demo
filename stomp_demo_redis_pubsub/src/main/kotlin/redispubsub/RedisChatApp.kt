package redispubsub

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisChatApp

fun main(args: Array<String>) {
    runApplication<RedisChatApp>(*args)
}