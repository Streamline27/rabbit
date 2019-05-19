package lv.citadele.producer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class Consumer {
    val LOG = LoggerFactory.getLogger(Consumer::class.java)

    @RabbitListener(queues = ["q.rabbit.simple-direct"])
    fun getFromSimpleDirectQueue(message: String) {
        LOG.warn("Consumed message from queue:[q.rabbit.simple-direct], message:[$message]")
    }

    @RabbitListener(queues = ["q.rabbit.after-delay-destination"])
    fun getFromAfterDelayDestinationQueue(message: String) {
        LOG.warn("Consumed message from queue:[q.rabbit.after-delay-destination], message:[$message]")
    }
}