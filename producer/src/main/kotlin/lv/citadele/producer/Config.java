package lv.citadele.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Config {

    public static final String EXCHANGE_NAME_DEFAULT = "";
    public static final String QUEUE_NAME_DELAYED_MESSAGE_STORE = "q.rabbit.delayed-message-store";
    public static final String QUEUE_NAME_AFTER_DELAY_DESTINATION = "q.rabbit.after-delay-destination";

    public static final String QUEUE_NAME_SIMPLE_DIRECT = "q.rabbit.simple-direct";

    @Bean
    public Queue queueDelayMessageStore() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-queue-mode", "lazy");
        arguments.put("x-dead-letter-exchange", EXCHANGE_NAME_DEFAULT);
        arguments.put("x-dead-letter-routing-key", QUEUE_NAME_AFTER_DELAY_DESTINATION);

        return new Queue(QUEUE_NAME_DELAYED_MESSAGE_STORE, true, false, false, arguments);
    }

    @Bean
    public Queue queueAfterDelayDestination() {
        return new Queue(QUEUE_NAME_AFTER_DELAY_DESTINATION);
    }

    @Bean
    public Queue queueSimpleDirect() {
        return new Queue(QUEUE_NAME_SIMPLE_DIRECT);
    }
}
