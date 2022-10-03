package net.dancier.chatdancer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ChatdancerApplication

fun main(args: Array<String>) {
	runApplication<ChatdancerApplication>(*args)
}
