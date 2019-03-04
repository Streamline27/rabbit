package lv.citadele.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    val LOG = LoggerFactory.getLogger(Consumer::class.java)

    @RabbitListener(queues = ["hello-world"])
    fun receiveMessage(message: String) {
        LOG.warn(message)
    }
}