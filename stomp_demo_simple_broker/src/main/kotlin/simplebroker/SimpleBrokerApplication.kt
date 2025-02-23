package simplebroker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SimpleBrokerApplication

fun main(args: Array<String>) {
    runApplication<SimpleBrokerApplication>(*args)
}