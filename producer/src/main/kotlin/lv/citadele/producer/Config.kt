package lv.citadele.producer

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import java.util.HashMap

@Configuration
class Config {

    @Bean
    fun queueDelayMessageStore(): Queue {
        val arguments = HashMap<String, Any>()
        arguments["x-queue-mode"] = "lazy"
        arguments["x-dead-letter-exchange"] = EXCHANGE_NAME_DEFAULT
        arguments["x-dead-letter-routing-key"] = QUEUE_NAME_AFTER_DELAY_DESTINATION

        return Queue(QUEUE_NAME_DELAYED_MESSAGE_STORE, true, false, false, arguments)
    }

    @Bean
    fun queueAfterDelayDestination(): Queue {
        return Queue(QUEUE_NAME_AFTER_DELAY_DESTINATION)
    }

    @Bean
    fun queueSimpleDirect(): Queue {
        return Queue(QUEUE_NAME_SIMPLE_DIRECT)
    }

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
        return RabbitAdmin(connectionFactory)
    }

    companion object {
        val EXCHANGE_NAME_DEFAULT = ""
        val QUEUE_NAME_DELAYED_MESSAGE_STORE = "q.rabbit.delayed-message-store"
        val QUEUE_NAME_AFTER_DELAY_DESTINATION = "q.rabbit.after-delay-destination"
        val QUEUE_NAME_SIMPLE_DIRECT = "q.rabbit.simple-direct"
    }
}
