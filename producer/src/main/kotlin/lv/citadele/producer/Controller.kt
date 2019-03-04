package lv.citadele.producer

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val rabbitTemplate : RabbitTemplate
) {

    @GetMapping("/ping")
    fun ping() = "ping"

    @GetMapping("/send")
    fun send() {
        rabbitTemplate.convertAndSend("hello-world-exchange", "", "Hello world")
    }
}