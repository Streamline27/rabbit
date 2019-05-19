package lv.citadele.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val rabbitTemplate : RabbitTemplate
) {
    private val log = LoggerFactory.getLogger(Controller::class.java)

    @GetMapping("/ping")
    fun ping() = "Ping from producer"

    @GetMapping("/send/{message}")
    fun send(@PathVariable message: String): String {
        log.info("Sending message:[$message] to queue:[${Config.QUEUE_NAME_SIMPLE_DIRECT}]")
        rabbitTemplate.convertAndSend("", Config.QUEUE_NAME_SIMPLE_DIRECT, message)
        return message
    }

    @GetMapping("/send-delay/{message}")
    fun sendDelay(@PathVariable message: String): String {
        log.info("Sending message:[$message] to queue:[${Config.QUEUE_NAME_DELAYED_MESSAGE_STORE}]")
        rabbitTemplate.convertAndSend("", Config.QUEUE_NAME_DELAYED_MESSAGE_STORE, message, ::addDelayHeader)
        return message
    }

    @GetMapping("/send-delay-long/{message}")
    fun sendDelayLong(@PathVariable message: String): String {
        log.info("Sending message:[$message] to queue:[${Config.QUEUE_NAME_DELAYED_MESSAGE_STORE}]")
        rabbitTemplate.convertAndSend("", Config.QUEUE_NAME_DELAYED_MESSAGE_STORE, message, ::addDelayLongHeader)
        return message
    }

    private fun addDelayHeader(rabbitMessage: Message) = rabbitMessage.apply {
        messageProperties.expiration = 3000.toString()
    }

    private fun addDelayLongHeader(rabbitMessage: Message) = rabbitMessage.apply {
        messageProperties.expiration = 40000.toString()
    }
}