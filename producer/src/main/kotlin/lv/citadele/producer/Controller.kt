package lv.citadele.producer

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val rabbitTemplate : RabbitTemplate
) {
    @GetMapping("/ping")
    fun ping() = "Ping from producer"

    @GetMapping("/send/{message}")
    fun send(@PathVariable message: String): String {
        rabbitTemplate.convertAndSend("", Config.QUEUE_NAME_SIMPLE_DIRECT, message)
        return message
    }

    @GetMapping("/send-delay/{message}")
    fun sendDelay(@PathVariable message: String): String {
        rabbitTemplate.convertAndSend("", Config.QUEUE_NAME_DELAYED_MESSAGE_STORE, message, ::addDelayHeader)
        return message
    }

    private fun addDelayHeader(rabbitMessage: Message) = rabbitMessage.apply {
        messageProperties.expiration = 15000.toString()
    }
}